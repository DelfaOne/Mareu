package com.example.mareu.service;

import androidx.lifecycle.MutableLiveData;

import com.example.mareu.repository.Meeting;

public interface MeetingApiService {

    MutableLiveData<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void addMeeting(Meeting meeting);
}
