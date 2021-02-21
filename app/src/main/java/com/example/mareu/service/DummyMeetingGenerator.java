package com.example.mareu.service;

import com.example.mareu.repository.Meeting;

import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, "Réunion A", "14h00", "maxime@lamzone, fadel@foudi"),

    );
}
