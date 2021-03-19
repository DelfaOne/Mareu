package com.example.mareu.addmeeting;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mareu.R;
import com.example.mareu.repository.MeetingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddMeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository;
    private final Application application;
    private final MutableLiveData<AddMeetingViewState> _viewStateLiveData = new MutableLiveData<>();
    public final LiveData<AddMeetingViewState> viewStateLiveData = _viewStateLiveData;

    private String subject;

    private  String location;

    private String date;

    private String hours;

    private String email;

    public AddMeetingViewModel(MeetingRepository meetingRepository, Application application) {
        this.meetingRepository = meetingRepository;
        this.application = application;
    }

    public void onSubjectChange(String subject) {
        this.subject = subject;
        controlInput();
    }

    public void onPlaceChange(String location) {
        this.location = location;
        controlInput();
    }

    public void onDateChange(String date) {
        this.date = date;
        controlInput();
    }

    public void onHoursChange(String hours) {
        this.hours = date;
        controlInput();
    }
    
    public void onEmailChange(String email) {
        this.email = email;
        controlInput();
    }

    public void onButtonAddClick() {
        meetingRepository.addMeetingItem(
                subject,
                location,
                convertDate(date),
                convertTime(hours),
                email
        );
    }

    public LocalDate convertDate(String dateString) {
        DateTimeFormatter formatter;
        if (!(dateString.charAt(1) == ' ')) {
            formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        } else
            formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

    public LocalTime convertTime(String timeString) {
        LocalTime yo = LocalTime.now();
       return yo;
    }

    private void controlInput() {
        String subjectError = null;
        if (subject == null || subject.isEmpty()) {
            subjectError = application.getString(R.string.error_subject_missing);
        }
        _viewStateLiveData.setValue(new AddMeetingViewState(
                subject,
                subjectError,
                location,
                date,
                email
        ));
    }
}
