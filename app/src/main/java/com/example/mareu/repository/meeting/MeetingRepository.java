package com.example.mareu.repository.meeting;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mareu.data.MeetingDataStore;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRepository {

    private MeetingDataStore apiService;

    public MeetingRepository(MeetingDataStore apiService) {
        this.apiService = apiService;
    }

    public void deleteMeetingItem(int meetingId) {
        apiService.deleteMeeting(meetingId);
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

    public LiveData<List<Meeting>> getMeetings() {
        return apiService.getMeetingsLiveData();
    }

}
