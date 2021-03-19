package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class DummyMeetingGenerator {

    public static void fillDummyMeeting(ArrayList<Meeting> arrayListMeeting) {
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Peach", LocalDate.now(), LocalTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Mario", LocalDate.now(), LocalTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Wario", LocalDate.now(), LocalTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Bowser", LocalDate.now(), LocalTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Luigi", LocalDate.now(), LocalTime.now(), "maxime@lamzone, fadel@foudi"));
    }

    public static ArrayList<Meeting> getDummyMeeting() {
        ArrayList<Meeting> arrayList = new ArrayList<>();
        fillDummyMeeting(arrayList);
        return arrayList;
    }
}
