package com.example.mareu.addmeeting;

import androidx.annotation.Nullable;

public class AddMeetingViewState {

    private final String subject;

    @Nullable
    private final String subjectError;

    private final String location;
    @Nullable
    private final String locationError;

    private final String date;

    private final String dateError;

    private final String time;

    private final String email;
    @Nullable
    private final String emailError;

    public AddMeetingViewState(String subject,
                               @Nullable String subjectError,
                               String location,
                               @Nullable String locationError,
                               String date, String dateError, String time, String email,
                               @Nullable String emailError) {
        this.subject = subject;
        this.subjectError = subjectError;
        this.location = location;
        this.locationError = locationError;
        this.date = date;
        this.dateError = dateError;
        this.time = time;
        this.email = email;
        this.emailError = emailError;
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

    @Nullable
    public String getLocationError() { return locationError; }

    @Nullable
    public String getEmailError() { return emailError; }

    public String getDateError() { return dateError; }
}
