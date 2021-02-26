package com.example.mareu.service;
import com.example.mareu.repository.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void addMeeting(Meeting meeting);
}
