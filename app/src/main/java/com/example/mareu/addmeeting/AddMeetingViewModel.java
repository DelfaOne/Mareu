package com.example.mareu.addmeeting;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mareu.R;
import com.example.mareu.repository.MeetingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class AddMeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository;
    private final Application application;
    private final MutableLiveData<AddMeetingViewState> _viewStateLiveData = new MutableLiveData<>();
    public final LiveData<AddMeetingViewState> viewStateLiveData = _viewStateLiveData;

    private String subject;

    private  String location;

    private LocalDate date;

    private LocalTime time;

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

    public void onDateChange(Long epoch) {
        this.date = LocalDateTime.ofEpochSecond(epoch / 1000, 0, ZoneOffset.UTC).toLocalDate();
        controlInput();
    }

    public void onTimeChange(int hour, int minute) {
        this.time = LocalTime.of(hour, minute);
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
                convertDate(),
                email
        );
    }

    private LocalDateTime convertDate() {
        return LocalDateTime.of(date, time);
    }

    private void controlInput() {
        String subjectError = null;
        String dateError = null;
        if (subject == null || subject.isEmpty()) {
            subjectError = application.getString(R.string.error_subject_missing);
        }
        if (date == null || time == null || convertDate().isBefore(LocalDateTime.now())) {
            dateError = application.getString(R.string.error_date_missing);
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
