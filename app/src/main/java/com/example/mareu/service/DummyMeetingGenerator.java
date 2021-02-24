package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

   /* public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, "Réunion A", lieu, "14h00", "maxime@lamzone, fadel@foudi"),
            new Meeting(1, "Réunion A", lieu, "14h00", "maxime@lamzone, fadel@foudi"),
            new Meeting(1, "Réunion A", lieu, "14h00", "maxime@lamzone, fadel@foudi"),
            new Meeting(1, "Réunion A", lieu, "14h00", "maxime@lamzone, fadel@foudi"),
            new Meeting(1, "Réunion A", lieu, "14h00", "maxime@lamzone, fadel@foudi"),
            new Meeting(1, "Réunion A", lieu, "14h00", "maxime@lamzone, fadel@foudi")
    );
*/
    public static List<Meeting> getDummyMeeting() {
        return new ArrayList<>();
    }
}
