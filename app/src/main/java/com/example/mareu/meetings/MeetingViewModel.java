package com.example.mareu.meetings;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mareu.repository.Meeting;
import com.example.mareu.repository.MeetingRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository;
    private final MediatorLiveData<List<MeetingViewState>> meetingViewStateMediatorLiveData = new MediatorLiveData<>();
    private final MutableLiveData<Boolean> isSortingStateAscendantLiveData = new MutableLiveData<>();
    LiveData<List<Meeting>> meetingsLiveData;

    public MeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
        meetingsLiveData = meetingRepository.getMeetings();
        meetingViewStateMediatorLiveData.addSource(meetingsLiveData, meetings -> {
            combine(meetings, isSortingStateAscendantLiveData.getValue());
        });
        meetingViewStateMediatorLiveData.addSource(isSortingStateAscendantLiveData, isSortingStateAscendant -> {
            combine(meetingsLiveData.getValue(), isSortingStateAscendant);
        });
    }

    private void combine(@Nullable List<Meeting> meetings, @Nullable Boolean isSortingStateAscendant) {
        if (meetings == null) {
            return;
        }
        List<MeetingViewState> results = new ArrayList<>();
        List<Meeting> copiedMeetings = new ArrayList<>(meetings);
        if (isSortingStateAscendant != null) {
            if (isSortingStateAscendant) { //
                Collections.sort(copiedMeetings, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            } else {
                Collections.sort(copiedMeetings, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
            }
        }
        for (Meeting itemMeeting : copiedMeetings) {
            results.add(map(itemMeeting));
        }
        meetingViewStateMediatorLiveData.setValue(results);
    }

    public void onDateRangeSelected(Pair<Long, Long> selectedDates) {
        if (selectedDates.first != null && selectedDates.second != null) {

            LocalDate startDate = Instant.ofEpochMilli(selectedDates.first).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = Instant.ofEpochMilli(selectedDates.second).atZone(ZoneId.systemDefault()).toLocalDate();

            List<MeetingViewState> results = new ArrayList<>();
            List<Meeting> actualMeetings = new ArrayList<>();

            if (meetingsLiveData.getValue() != null) {
                actualMeetings = meetingsLiveData.getValue();
            }

            for (Meeting meeting : actualMeetings) {
                LocalDate meetingDate = meeting.getDate().toLocalDate();
                if (meetingDate.isBefore(endDate.plusDays(1)) && meetingDate.isAfter(startDate.minusDays(1))) {
                    results.add(map(meeting));
                }
            }

            meetingViewStateMediatorLiveData.setValue(results);
        }
    }

    public void onLocationChoiceSelected(List<String> itemsSelected) {
        List<MeetingViewState> results = new ArrayList<>();
        List<Meeting> actualMeetings = new ArrayList<>();
        System.out.println(itemsSelected.toString());

        if (meetingsLiveData.getValue() != null) {
            actualMeetings = meetingsLiveData.getValue();
        }

        for (Meeting meeting : actualMeetings) {
            String location = meeting.getLieu();
            for (int i = 0; i < itemsSelected.size(); i++) {
                if (location.equalsIgnoreCase(itemsSelected.get(i)))
                    results.add(map(meeting));
            }
        }

        meetingViewStateMediatorLiveData.setValue(results);
    }

    public void onDateSortingButtonSelected() {
        Boolean previousValue = isSortingStateAscendantLiveData.getValue();
        if (previousValue == null) {
            isSortingStateAscendantLiveData.setValue(true);
        } else {
            isSortingStateAscendantLiveData.setValue(!previousValue);
        }
    }

    public LiveData<List<MeetingViewState>> getMeetingViewStateLiveData() {
        return meetingViewStateMediatorLiveData;
    }

    private MeetingViewState map(Meeting meeting) {
        return new MeetingViewState(
                meeting.getId(),
                meeting.getReunionSubject(),
                meeting.getLieu(),
                formatDateTime(meeting.getDate()),
                meeting.getParticipants());
    }

    private String formatDateTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy - HH:mm");
        return date.format(formatter);
    }

    public void deleteItem(MeetingViewState meetingViewState) {
        meetingRepository.deleteMeetingItem(meetingViewState);
    }
}
