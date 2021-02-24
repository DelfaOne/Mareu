package com.example.mareu.viewmodel;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

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

    public MeetingViewState(int id, String reunionSubject, String lieu, String date, String participants) {
        this.id = id;
        this.reunionSubject = reunionSubject;
        this.lieu = lieu;
        this.date = date;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public String getReunionSubject() {
        return reunionSubject;
    }

    public String getDate() {
        return date;
    }

    public String getParticipants() {
        return participants;
    }
}
