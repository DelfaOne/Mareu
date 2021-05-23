package com.example.mareu.addmeeting;

import androidx.annotation.Nullable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMeetingViewState that = (AddMeetingViewState) o;
        return Objects.equals(subject, that.subject) &&
                Objects.equals(subjectError, that.subjectError) &&
                Objects.equals(location, that.location) &&
                Objects.equals(locationError, that.locationError) &&
                Objects.equals(date, that.date) &&
                Objects.equals(dateError, that.dateError) &&
                Objects.equals(time, that.time) &&
                Objects.equals(email, that.email) &&
                Objects.equals(emailError, that.emailError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, subjectError, location, locationError, date, dateError, time, email, emailError);
    }

    @Override
    public String toString() {
        return "AddMeetingViewState{" +
                "subject='" + subject + '\'' +
                ", subjectError='" + subjectError + '\'' +
                ", location='" + location + '\'' +
                ", locationError='" + locationError + '\'' +
                ", date='" + date + '\'' +
                ", dateError='" + dateError + '\'' +
                ", time='" + time + '\'' +
                ", email='" + email + '\'' +
                ", emailError='" + emailError + '\'' +
                '}';
    }
}
