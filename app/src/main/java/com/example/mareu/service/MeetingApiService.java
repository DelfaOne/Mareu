package com.example.mareu.service;
import androidx.lifecycle.LiveData;

import com.example.mareu.repository.meeting.Meeting;

import java.util.List;
import java.util.Map;

public interface MeetingApiService {

    LiveData<List<Meeting>> getMeetings();

    LiveData<Map<String, Boolean>> getRooms();

    void deleteMeeting(int id);

    void addMeeting(Meeting meeting);

    int generateNewMeetingId();
}
