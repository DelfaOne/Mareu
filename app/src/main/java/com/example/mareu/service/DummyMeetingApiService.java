package com.example.mareu.service;
import com.example.mareu.repository.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private ArrayList<Meeting> meetingList = DummyMeetingGenerator.getDummyMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return meetingList;
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
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingList.add(meeting);
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
