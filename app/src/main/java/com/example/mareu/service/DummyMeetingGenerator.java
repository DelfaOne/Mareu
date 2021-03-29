package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class DummyMeetingGenerator {

    public static void fillDummyMeeting(ArrayList<Meeting> arrayListMeeting) {
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Salle Peach", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Salle Mario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Salle Wario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Salle Bowser", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Salle Luigi", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
    }

    public static ArrayList<Meeting> getDummyMeeting() {
        ArrayList<Meeting> arrayList = new ArrayList<>();
        fillDummyMeeting(arrayList);
        return arrayList;
    }
}
