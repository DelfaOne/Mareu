package com.example.mareu.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mareu.meetings.MeetingViewState;
import com.example.mareu.service.MeetingApiService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    private MeetingApiService apiService;

    public MeetingRepository(MeetingApiService apiService) {
        this.apiService = apiService;
    }

    public void deleteMeetingItem(MeetingViewState meetingViewState) {
        apiService.deleteMeeting(meetingViewState.getId());
    }

    public void addMeetingItem(@NonNull String reunionSubject, @NonNull String lieu, @NonNull LocalDateTime date, @NonNull String participants) {
        apiService.addMeeting(new Meeting(
                apiService.generateNewMeetingId(),
                reunionSubject,
                lieu,
                date,
                participants
        ));
    }

    public LiveData<List<Meeting>> getMeetings() {
        return apiService.getMeetings();
    }

    /*public List<Meeting> getMeetingByDate(LocalDate startDate, LocalDate endDate) {
        List<Meeting> meetingListByDate = new ArrayList<>();
        List<Meeting> initialList = apiService.getMeeting();

        for (Meeting meeting : initialList) {
            LocalDate meetingDate = meeting.getDate().toLocalDate();
            if (meetingDate.isBefore(endDate) && meetingDate.isAfter(startDate)) {
                meetingListByDate.add(meeting);
            }
        }
        return meetingListByDate;
    }*/

    /*public List<Meeting> getMeetingByLocation(String location) {
        List<Meeting> meetingListByLocation = new ArrayList<>();
        List<Meeting> initialList = apiService.getMeetings();

        for (Meeting meeting : initialList) {
            if (meeting.getLieu().equalsIgnoreCase(location)) {
                meetingListByLocation.add(meeting);
            }
        }
        return meetingListByLocation;
    }*/


}
