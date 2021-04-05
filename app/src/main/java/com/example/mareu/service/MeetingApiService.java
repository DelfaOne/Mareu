package com.example.mareu.service;
import androidx.lifecycle.LiveData;

import com.example.mareu.repository.Meeting;

import java.util.List;

public interface MeetingApiService {

    LiveData<List<Meeting>> getMeetings();

    void deleteMeeting(int id);

    void addMeeting(Meeting meeting);

    int generateNewMeetingId();
}
