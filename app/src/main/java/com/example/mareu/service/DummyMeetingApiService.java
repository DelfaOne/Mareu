package com.example.mareu.service;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.repository.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private final List<Meeting> meetingList = DummyMeetingGenerator.getDummyMeeting();
    private final MutableLiveData<List<Meeting>> meetingListLiveData = new MutableLiveData<>();

    public DummyMeetingApiService() {
        meetingListLiveData.setValue(meetingList);
    }

    @Override
    public LiveData<List<Meeting>> getMeetings() {
        return meetingListLiveData;
    }

    @Override
    public void deleteMeeting(int meetingId) {
        Meeting match = null;
        for (Meeting meeting: meetingList) {
            if (meeting.getId() == meetingId) {
                match = meeting;
            }
        }
        if (match != null) {
            meetingList.remove(match);
        }
        meetingListLiveData.setValue(meetingList);
    }

    @Override
    public void addMeeting(Meeting meeting) {

        meetingList.add(meeting);
        meetingListLiveData.setValue(meetingList);
    }

    @Override
    public int generateNewMeetingId() {
        int highestId = 0;
        for (Meeting meeting : meetingList) {
            if (highestId < meeting.getId()) {
                highestId = meeting.getId();
            }
        }
        return highestId + 1;
    }
}
