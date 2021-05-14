package com.example.mareu.roomselector;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mareu.repository.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomSelectorViewModel extends ViewModel {

    @NonNull
    private final RoomRepository roomRepository;

    public final LiveData<List<RoomSelectorViewState>> roomSelectorViewStateLiveData; //Liste des rooms pour l'adapter

    public RoomSelectorViewModel(@NonNull RoomRepository roomRepository) {
        this.roomRepository = roomRepository;

        this.roomSelectorViewStateLiveData = Transformations.map(roomRepository.getRoomsLiveData(), roomSelectedMap -> {
            List<RoomSelectorViewState> roomsList = new ArrayList<>();

            for (Map.Entry<String, Boolean> entry : roomSelectedMap.entrySet()) {
                roomsList.add(new RoomSelectorViewState(
                        entry.getKey(),
                        entry.getValue()
                ));
            }
            return roomsList;
        });
    }

    public LiveData<List<RoomSelectorViewState>> getRoomSelectorViewStateLiveData() {
        return roomSelectorViewStateLiveData;
    }

    public void onCheckedChange(boolean isChecked, String roomName) {
        roomRepository.toggleRoomChecked(isChecked, roomName);
    }
}
