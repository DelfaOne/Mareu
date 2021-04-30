package com.example.mareu.data;
import androidx.lifecycle.LiveData;

import com.example.mareu.repository.meeting.Meeting;

import java.util.List;

public interface MeetingDataStore {

    LiveData<List<Meeting>> getMeetingsLiveData();

    void deleteMeeting(int id);

    void addMeeting(Meeting meeting);

    int generateNewMeetingId();
}
