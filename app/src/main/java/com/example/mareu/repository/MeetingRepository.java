package com.example.mareu.repository;

import androidx.annotation.NonNull;

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

    public List<Meeting> getMeeting() {
        return apiService.getMeeting();
    }

    public List<Meeting> getMeetingByDate(LocalDate startDate, LocalDate endDate) {
        List<Meeting> meetingListByDate = new ArrayList<>();
        List<Meeting> initialList = apiService.getMeeting();

        for (Meeting meeting : initialList) {
            LocalDate meetingDate = LocalDate.of(meeting.getDate().getYear(), meeting.getDate().getMonth(), meeting.getDate().getDayOfMonth());
            boolean shouldAdd = meetingDate.isBefore(endDate) && meetingDate.isAfter(startDate);
            if (shouldAdd) {
                meetingListByDate.add(meeting);
            }
        }
        return meetingListByDate;
    }

    public List<Meeting> getMeetingByLocation(String location) {
        List<Meeting> meetingListByLocation = new ArrayList<>();
        List<Meeting> initialList = apiService.getMeeting();

        for (Meeting meeting : initialList) {
            boolean shouldAdd = meeting.getLieu().equals(location);
            if (shouldAdd) {
                meetingListByLocation.add(meeting);
            }
        }
        return meetingListByLocation;
    }


}
