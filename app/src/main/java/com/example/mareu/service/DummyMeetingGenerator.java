package com.example.mareu.service;

import com.example.mareu.repository.meeting.Meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DummyMeetingGenerator {

    public static void fillDummyMeeting(ArrayList<Meeting> arrayListMeeting) {
        arrayListMeeting.add(new Meeting(1, "Réunion A", "Peach", LocalDateTime.now().plusDays(1), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(2, "Réunion B", "Mario", LocalDateTime.now().plusDays(2), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(3, "Réunion C", "Wario", LocalDateTime.now().plusDays(3), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now().plusDays(4), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now().plusDays(5), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(6, "Réunion F", "Peach", LocalDateTime.now().plusDays(6), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(7, "Réunion G", "Mario", LocalDateTime.now().plusDays(7), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(8, "Réunion H", "Wario", LocalDateTime.now().plusDays(8), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(9, "Réunion I", "Bowser", LocalDateTime.now().plusDays(9), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(10, "Réunion J", "Luigi", LocalDateTime.now().plusDays(10), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(11, "Réunion K", "Peach", LocalDateTime.now().plusDays(11), "maxime@lamzone, fadel@foudi"));
        arrayListMeeting.add(new Meeting(12, "Réunion L", "Mario", LocalDateTime.now().plusDays(12), "maxime@lamzone, fadel@foudi"));
    }

    public static Map<String, Boolean> getRooms() {
        Map<String, Boolean> roomMap = new HashMap<>();
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
