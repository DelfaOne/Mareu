package com.example.mareu.addmeeting;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddMeetingViewState {

    private final String subject;

    @Nullable
    private final String subjectError;

    private final String location;

    private final String date;

    private final String time;

    private final String email;

    public AddMeetingViewState(String subject,@Nullable String subjectError, String location, String date,String time, String email) {
        this.subject = subject;
        this.subjectError = subjectError;
        this.location = location;
        this.date = date;
        this.time = time;
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    @Nullable
    public String getSubjectError() {
        return subjectError;
    }

    public String getTime() { return time; }
}
