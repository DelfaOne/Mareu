package com.example.mareu.meetings;

import android.graphics.Color;

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
    private final String participants;

    public MeetingViewState(@NonNull int id, @NonNull String reunionSubject, @NonNull String lieu, @NonNull String date, @NonNull String participants) {
        this.id = id;
        this.reunionSubject = reunionSubject;
        this.lieu = lieu;
        this.date = date;
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

    public String getDate() {
        //return LocalDateTime.parse(this.date).toString();
        return date;
    }

    public String getParticipants() {
        return participants;
    }

}
