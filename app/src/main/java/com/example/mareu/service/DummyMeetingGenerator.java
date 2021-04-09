package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class DummyMeetingGenerator {

    public static void fillDummyMeeting(ArrayList<Meeting> arrayListMeeting) {
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Peach", LocalDateTime.now().plusDays(1), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Mario", LocalDateTime.now().plusDays(2), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Wario", LocalDateTime.now().plusDays(3), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now().plusDays(4), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now().plusDays(5), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Peach", LocalDateTime.now().plusDays(6), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Mario", LocalDateTime.now().plusDays(7), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Wario", LocalDateTime.now().plusDays(8), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now().plusDays(9), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now().plusDays(10), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Peach", LocalDateTime.now().plusDays(11), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Mario", LocalDateTime.now().plusDays(12), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Wario", LocalDateTime.now().plusDays(13), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now().plusDays(14), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now().plusDays(15), "maxime@lamzone, fadel@foudi"));
    }

    public static ArrayList<Meeting> getDummyMeeting() {
        ArrayList<Meeting> arrayList = new ArrayList<>();
        fillDummyMeeting(arrayList);
        return arrayList;
    }
}
