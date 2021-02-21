package com.example.mareu;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mareu.repository.SingleStringRepo;

public class MeetingViewModel extends ViewModel {

    private final SingleStringRepo singleStringRepo;

    public MeetingViewModel(SingleStringRepo singleStringRepo) {
        this.singleStringRepo = singleStringRepo;
    }

    public LiveData<String> getVIewStateLiveData() {
        return Transformations.map(singleStringRepo.getCurrentStringLiveData(), input -> "Current time is : " + input);
    }

    public void onButtonPressed() {
        singleStringRepo.setNewString("" + System.currentTimeMillis());
    }
}
