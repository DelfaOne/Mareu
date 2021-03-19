package com.example.mareu.meetings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mareu.repository.Meeting;
import com.example.mareu.repository.MeetingRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository;
    private final MutableLiveData<List<MeetingViewState>> _meetingViewStateLiveData = new MutableLiveData<>();
    public final LiveData<List<MeetingViewState>> meetingViewStateLiveData = _meetingViewStateLiveData;

    public MeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public void loadData() {
        List<Meeting> meetingList = meetingRepository.getMeeting();
        List<MeetingViewState> viewState = map(meetingList);
        _meetingViewStateLiveData.setValue(viewState);
    }

    private List<MeetingViewState> map(List<Meeting> meetings) {
        List<MeetingViewState> results = new ArrayList<>();
        for (Meeting meeting : meetings) {
            results.add(new MeetingViewState(
                    meeting.getId(),
                    meeting.getReunionSubject(),
                    meeting.getLieu(),
                    formatDateTime(meeting.getDate()),
                    formatHoursTime(meeting.getHours()),
                    meeting.getParticipants()
            ));
        }
        return results;
    }

    private String formatDateTime(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    private String formatHoursTime(LocalTime date) {
        String yo = "yo";
        return yo;
    }

    public void deleteItem(MeetingViewState meetingViewState) {
        meetingRepository.deleteMeetingItem(meetingViewState);
        loadData();
    }
}
