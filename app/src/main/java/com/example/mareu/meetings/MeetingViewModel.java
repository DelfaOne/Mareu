package com.example.mareu.meetings;

import android.app.Application;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mareu.R;
import com.example.mareu.repository.meeting.Meeting;
import com.example.mareu.repository.meeting.MeetingRepository;
import com.example.mareu.repository.room.RoomRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.mareu.R.color.Bowser;
import static com.example.mareu.R.color.Luigi;
import static com.example.mareu.R.color.Mario;
import static com.example.mareu.R.color.Peach;
import static com.example.mareu.R.color.Wario;

public class MeetingViewModel extends ViewModel {

    @NonNull
    private final Application application;
    @NonNull
    private final MeetingRepository meetingRepository;

    private final MediatorLiveData<List<MeetingViewState>> meetingViewStateMediatorLiveData = new MediatorLiveData<>();
    private final MutableLiveData<Pair<Long, Long>> dateRangeLiveData = new MutableLiveData<>();

    public MeetingViewModel(
        @NonNull Application application,
        @NonNull MeetingRepository meetingRepository,
        @NonNull RoomRepository roomRepository
    ) {
        this.meetingRepository = meetingRepository;
        this.application = application;

        LiveData<List<Meeting>> meetingsLiveData = meetingRepository.getMeetings();
        LiveData<Map<String, Boolean>> roomCheckedMapLiveData = roomRepository.getRoomsLiveData();

        meetingViewStateMediatorLiveData.addSource(meetingsLiveData, meetings -> {
            combine(meetings, dateRangeLiveData.getValue(), roomCheckedMapLiveData.getValue());
        });
        meetingViewStateMediatorLiveData.addSource(dateRangeLiveData, dateRange -> {
            combine(meetingsLiveData.getValue(), dateRange, roomCheckedMapLiveData.getValue());
        });
        meetingViewStateMediatorLiveData.addSource(roomCheckedMapLiveData, roomCheckedMap -> {
            combine(meetingsLiveData.getValue(), dateRangeLiveData.getValue(), roomCheckedMap);
        });
    }

    private void combine(
        @Nullable List<Meeting> meetings,
        @Nullable Pair<Long, Long> dateRange,
        @Nullable Map<String, Boolean> roomCheckedMap) {
        if (meetings == null || roomCheckedMap == null) {
            return;
        }

        List<MeetingViewState> results = new ArrayList<>();

        boolean isDateRangeSelected = false;
        if (dateRange != null && (dateRange.first != null || dateRange.second != null)) {
            isDateRangeSelected = true;
        }

        boolean isAtLeastOneRoomSelected = false;
        for (Map.Entry<String, Boolean> roomCheckedEntry : roomCheckedMap.entrySet()) {
            if (roomCheckedEntry.getValue() != null && roomCheckedEntry.getValue()) {
                isAtLeastOneRoomSelected = true;
            }
        }

        for (Meeting meeting : meetings) {
            boolean isInDateRange = isInDateRange(dateRange, meeting);

            boolean isInRoomSelection = isInRoomSelection(roomCheckedMap, meeting);

            if ((!isDateRangeSelected || isInDateRange) && (!isAtLeastOneRoomSelected || isInRoomSelection)) {
                results.add(map(meeting));
            }
        }

        meetingViewStateMediatorLiveData.setValue(results);
    }

    private boolean isInDateRange(
        @Nullable Pair<Long, Long> dateRange,
        @NonNull Meeting meeting
    ) {
        boolean isInDateRange = false;

        if (dateRange != null) {
            LocalDate startDate = Instant.ofEpochMilli(dateRange.first).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = Instant.ofEpochMilli(dateRange.second).atZone(ZoneId.systemDefault()).toLocalDate();

            LocalDate meetingDate = meeting.getDate().toLocalDate();

            isInDateRange = meetingDate.isBefore(endDate.plusDays(1)) && meetingDate.isAfter(startDate.minusDays(1));
        }

        return isInDateRange;
    }

    private boolean isInRoomSelection(
        @NonNull Map<String, Boolean> roomCheckedMap,
        @NonNull Meeting meeting
    ) {
        boolean isInRoomSelection = false;

        for (Map.Entry<String, Boolean> roomCheckedEntry : roomCheckedMap.entrySet()) {
            if (meeting.getLieu().equals(roomCheckedEntry.getKey())
                && roomCheckedEntry.getValue() != null
                && roomCheckedEntry.getValue()) {
                isInRoomSelection = true;
                break;
            }
        }
        return isInRoomSelection;
    }

    public void onDateRangeSelected(Pair<Long, Long> selectedDate) {
        dateRangeLiveData.setValue(selectedDate);
    }

    public LiveData<List<MeetingViewState>> getMeetingViewStateLiveData() {
        return meetingViewStateMediatorLiveData;
    }

    private MeetingViewState map(Meeting meeting) {
        String title = application.getString(
            R.string.meeting_title,
            meeting.getReunionSubject(),
            formatDateTime(meeting.getDate())
        );
        String roomName = application.getString(
            R.string.room_name,
            meeting.getLieu()
        );
        @ColorRes int avatarColor = getColor(meeting.getLieu());

        return new MeetingViewState(
            meeting.getId(),
            title,
            roomName,
            meeting.getParticipants(),
            avatarColor);
    }

    @ColorRes
    public int getColor(String rooms) {
        int color;
        switch (rooms) {
            case "Peach":
                color = Peach;
                break;
            case "Mario":
                color = Mario;
                break;
            case "Wario":
                color = Wario;
                break;
            case "Bowser":
                color = Bowser;
                break;
            case "Luigi":
                color = Luigi;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rooms);
        }
        return color;
    }

    private String formatDateTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy - HH:mm");
        return date.format(formatter);
    }

    public void deleteItem(int meetingId) {
        meetingRepository.deleteMeetingItem(meetingId);
    }
}
