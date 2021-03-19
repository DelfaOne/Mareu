package com.example.mareu.meetings;

import androidx.annotation.NonNull;

public class MeetingViewState {

    private final int id;
    @NonNull
    private final String reunionSubject;
    @NonNull
    private final String lieu;
    @NonNull
    private final String date;
    @NonNull
    private final String hours;
    @NonNull
    private final String participants;

    public MeetingViewState(@NonNull int id, @NonNull String reunionSubject, @NonNull String lieu, @NonNull String date, @NonNull String hours, @NonNull String participants) {
        this.id = id;
        this.reunionSubject = reunionSubject;
        this.lieu = lieu;
        this.date = date;
        this.hours = hours;
        this.participants = participants;
    }

    @NonNull
    public String getLieu() {
        return lieu;
    }

    public int getId() {
        return id;
    }

    public String getReunionSubject() {
        return reunionSubject;
    }

    public String getDate() { return date; }

    public String getHours() { return hours; }

    public String getParticipants() {
        return participants;
    }

}
