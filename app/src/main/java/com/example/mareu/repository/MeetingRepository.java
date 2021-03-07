package com.example.mareu.repository;

import com.example.mareu.DI.DI;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.viewmodel.MeetingViewState;

import java.util.List;

public class MeetingRepository {

    private MeetingApiService apiService = DI.getNeighbourApiService();

    public void deleteMeetingItem(MeetingViewState meetingViewState) {
        apiService.deleteMeeting(meetingViewState.getId());
    }

    public List<Meeting> getMeeting() {
        return apiService.getMeeting();
    }
}
