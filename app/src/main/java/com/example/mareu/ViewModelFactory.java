package com.example.mareu;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mareu.addmeeting.AddMeetingViewModel;
import com.example.mareu.meetings.MeetingViewModel;
import com.example.mareu.repository.MeetingRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;
    private final MeetingRepository meetingRepository;

    private ViewModelFactory(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                            new MeetingRepository()
                    );
                }
            }
        }
        return factory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeetingViewModel.class)) {
            return (T) new MeetingViewModel(meetingRepository);
        } else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(meetingRepository, MainApplication.getsApplication());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
