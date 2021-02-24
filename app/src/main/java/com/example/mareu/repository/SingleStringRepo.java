package com.example.mareu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SingleStringRepo {

    private MutableLiveData<List<Meeting>> currentStringMutableLiveData = new MutableLiveData<>();

    public void addNewMeeting() {
        List<Meeting> meetingList = currentStringMutableLiveData.getValue();
        if (meetingList == null) {
            meetingList = new ArrayList<>();
        }
        meetingList.add(new Meeting(meetingList.size(),
                "subject " + meetingList.size(),
                "Lille", LocalDateTime.now(),
                "fadel"));
        currentStringMutableLiveData.setValue(meetingList);
    }

    public LiveData<List<Meeting>> getCurrentStringLiveData() {
        return currentStringMutableLiveData;
    }
}
