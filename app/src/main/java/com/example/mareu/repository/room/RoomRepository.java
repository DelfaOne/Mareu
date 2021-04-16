package com.example.mareu.repository.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mareu.meetings.MeetingViewState;
import com.example.mareu.repository.meeting.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class RoomRepository {

    private MeetingApiService apiService;

    public RoomRepository(MeetingApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<Map<String, Boolean>> getRooms() {
        return apiService.getRooms();
    }


}
