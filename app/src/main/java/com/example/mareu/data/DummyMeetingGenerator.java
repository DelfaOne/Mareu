package com.example.mareu.data;

import com.example.mareu.repository.meeting.Meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class DummyMeetingGenerator {

    public static void fillDummyMeeting(ArrayList<Meeting> arrayListMeeting) {
        arrayListMeeting.add(new Meeting(0, "Réunion A", "Peach", getInitialDate().plusDays(1), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(1, "Réunion B", "Mario", getInitialDate().plusDays(2), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion C", "Wario", getInitialDate().plusDays(3), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion D", "Bowser", getInitialDate().plusDays(4), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion E", "Luigi", getInitialDate().plusDays(5), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion F", "Peach", getInitialDate().plusDays(6), "maxime@lamzone, fadel@foudi"));
    }

    private static LocalDateTime getInitialDate() {
        return LocalDateTime.of(2021, 4, 1, 12, 30);
    }

    public static TreeMap<String, Boolean> getRooms() {
        TreeMap<String, Boolean> roomMap = new TreeMap<>();
        roomMap.put("Peach", false);
        roomMap.put("Mario", false);
        roomMap.put("Wario", false);
        roomMap.put("Bowser", false);
        roomMap.put("Luigi", false);
        return roomMap;
    }

    public static ArrayList<Meeting> getDummyMeeting() {
        ArrayList<Meeting> arrayList = new ArrayList<>();
        fillDummyMeeting(arrayList);
        return arrayList;
    }





}
