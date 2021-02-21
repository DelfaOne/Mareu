package com.example.mareu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SingleStringRepo {

    private MutableLiveData<String> currentStringMutableLiveData = new MutableLiveData<>();

    public void setNewString(String value) {
        currentStringMutableLiveData.setValue(value);
    }

    public LiveData<String> getCurrentStringLiveData() {
        return currentStringMutableLiveData;
    }
}
