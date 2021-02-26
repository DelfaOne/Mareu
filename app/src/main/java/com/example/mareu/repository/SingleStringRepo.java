package com.example.mareu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.DI.DI;
import com.example.mareu.service.MeetingApiService;
import java.util.List;

public class SingleStringRepo {

    private MutableLiveData<List<Meeting>> currentStringMutableLiveData = new MutableLiveData<>();
    private MeetingApiService apiService = DI.getNeighbourApiService();

    /*public void addNewMeeting() {
        List<Meeting> meetingList = currentStringMutableLiveData.getValue();
        if (meetingList == null) {
            meetingList = new ArrayList<>();
        }
        meetingList.add(new Meeting(meetingList.size(),
                "subject " + meetingList.size(),
                "Lille", LocalDateTime.now(),
                "fadel"));
        currentStringMutableLiveData.setValue(meetingList);
    }*/

    public void fillData() {
        currentStringMutableLiveData.setValue(apiService.getMeeting());
    }

    public LiveData<List<Meeting>> getCurrentStringLiveData() {
        return currentStringMutableLiveData;
    }
}
