package com.example.mareu.repository;

import androidx.lifecycle.MutableLiveData;

public class Meeting {

    private int id;

    private String reunionSubject;

    private String lieu;

    private String date;

    private String participants;

    public Meeting(int id, String reunionSubject, String date, String participants) {
        this.id = id;
        this.reunionSubject = reunionSubject;
        this.date = date;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReunionSubject() {
        return reunionSubject;
    }

    public void setReunionSubject(String reunionSubject) {
        this.reunionSubject = reunionSubject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
