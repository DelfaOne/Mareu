package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class DummyMeetingGenerator {

    public static void fillDummyMeeting(ArrayList<Meeting> arrayListMeeting) {
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Peach", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Mario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Wario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
    }

    public static ArrayList<Meeting> getDummyMeeting() {
        ArrayList<Meeting> arrayList = new ArrayList<>();
        fillDummyMeeting(arrayList);
        return arrayList;
    }
}
