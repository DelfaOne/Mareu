package com.example.mareu.repository;

import androidx.annotation.NonNull;

import com.example.mareu.DI.DI;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.meetings.MeetingViewState;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRepository {

    private MeetingApiService apiService = DI.getNeighbourApiService();

    public void deleteMeetingItem(MeetingViewState meetingViewState) {
        apiService.deleteMeeting(meetingViewState.getId());
    }

    public void addMeetingItem(@NonNull String reunionSubject, @NonNull String lieu, @NonNull LocalDateTime date, @NonNull String participants) {
        apiService.addMeeting(new Meeting(
                apiService.generateNewMeetingId(),
                reunionSubject,
                lieu,
                date,
                participants
        ));
    }

    public List<Meeting> getMeeting() {

        return apiService.getMeeting();
    }


}
