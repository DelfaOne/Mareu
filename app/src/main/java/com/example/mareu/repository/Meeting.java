package com.example.mareu.repository;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting {

    private final int id;
    @NonNull
    private final String reunionSubject;
    @NonNull
    private final String lieu;
    @NonNull
    private final LocalDate date;
    @NonNull
    private final LocalTime hours;
    @NonNull
    private final String participants;

    public Meeting(@NonNull int id, @NonNull String reunionSubject, @NonNull String lieu, @NonNull LocalDate date, @NonNull LocalTime hours, @NonNull String participants) {
        this.id = id;
        this.reunionSubject = reunionSubject;
        this.lieu = lieu;
        this.date = date;
        this.hours = hours;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public String getReunionSubject() {
        return reunionSubject;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHours() { return hours; }

    public String getParticipants() {
        return participants;
    }

    public String getLieu() {
        return lieu;
    }
}
