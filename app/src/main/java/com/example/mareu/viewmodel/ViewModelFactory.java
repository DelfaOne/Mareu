package com.example.mareu.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mareu.repository.SingleStringRepo;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;
    private final SingleStringRepo singleStringRepo;

    private ViewModelFactory(SingleStringRepo singleStringRepo) {
        this.singleStringRepo = singleStringRepo;
    }

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                            new SingleStringRepo()
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
            return (T) new MeetingViewModel(singleStringRepo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
