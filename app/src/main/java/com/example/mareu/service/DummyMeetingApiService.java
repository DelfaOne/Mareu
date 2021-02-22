package com.example.mareu.service;

import androidx.lifecycle.MutableLiveData;

import com.example.mareu.repository.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> meetingList = DummyMeetingGenerator.getDummyMeeting();

    @Override
    public MutableLiveData<Meeting> getMeeting() {
        return null;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {

    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }
}
