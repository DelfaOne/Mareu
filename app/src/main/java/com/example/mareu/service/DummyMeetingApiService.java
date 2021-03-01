package com.example.mareu.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.repository.Meeting;
import com.example.mareu.viewmodel.MeetingViewState;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> meetingList = DummyMeetingGenerator.getDummyMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return meetingList;
    }

    @Override
    public void deleteMeeting(MeetingViewState meeting) {
        meetingList.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }
}
