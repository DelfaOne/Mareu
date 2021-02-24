package com.example.mareu.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;

public class Meeting {

    private final int id;
    @NonNull
    private final String reunionSubject;
    @NonNull
    private final String lieu;

    @NonNull
    public String getLieu() {
        return lieu;
    }

    @NonNull
    private final LocalDateTime date;
    @NonNull
    private final String participants;

    public Meeting(int id, String reunionSubject, String lieu, LocalDateTime date, String participants) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public String getParticipants() {
        return participants;
    }

}
