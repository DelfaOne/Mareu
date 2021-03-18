package com.example.mareu.service;
import com.example.mareu.repository.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    void deleteMeeting(int id);

    void addMeeting(Meeting meeting);

    int generateNewMeetingId();
}
