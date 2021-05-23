package com.example.mareu.repository.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.data.DummyMeetingGenerator;

import java.util.Map;
import java.util.TreeMap;

public class RoomRepository {
    private final TreeMap<String, Boolean> roomCheckedMap = DummyMeetingGenerator.getRooms();

    private final MutableLiveData<Map<String, Boolean>> roomListLiveData = new MutableLiveData<>(roomCheckedMap);

    public LiveData<Map<String, Boolean>> getRoomsLiveData() {
        return roomListLiveData;
    }

    public void toggleRoomChecked(boolean isChecked, String roomName) {
        roomCheckedMap.put(roomName, isChecked);

        roomListLiveData.setValue(roomCheckedMap);
    }
}
