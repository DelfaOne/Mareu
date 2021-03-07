package com.example.mareu.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
