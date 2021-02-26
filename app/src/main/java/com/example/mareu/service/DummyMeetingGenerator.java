package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

   public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, "Réunion A", "Peach", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
            new Meeting(2, "Réunion B", "Mario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
            new Meeting(3, "Réunion C", "Wario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
            new Meeting(4, "Réunion D", "Bowser", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
            new Meeting(5, "Réunion E", "Luigi", LocalDateTime.now(), "maxime@lamzone, fadel@foudi")
    );

    public static List<Meeting> getDummyMeeting() {
        return DUMMY_MEETING;
    }
}
