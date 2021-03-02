package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static ArrayList<Meeting> getDummyMeeting() {
        ArrayList<Meeting> arrayList = new ArrayList<>();
        arrayList.add(new Meeting(1, "Réunion A", "Peach", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayList.add(new Meeting(2, "Réunion B", "Mario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayList.add(new Meeting(3, "Réunion C", "Wario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayList.add(new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        arrayList.add(new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"));
        return arrayList;
    }
}
