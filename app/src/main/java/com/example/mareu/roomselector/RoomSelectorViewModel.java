package com.example.mareu.roomselector;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mareu.repository.meeting.Meeting;
import com.example.mareu.repository.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomSelectorViewModel extends ViewModel {

    @NonNull
    private final RoomRepository roomRepository;
    private final LiveData<List<RoomSelectorViewState>> roomSelectorViewState;

    public RoomSelectorViewModel(@NonNull RoomRepository roomRepository) {
        this.roomRepository = roomRepository;

        this.roomSelectorViewState = Transformations.map(roomRepository.getRooms(), roomSelectedMap -> {
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

    public LiveData<List<RoomSelectorViewState>> getRoomSelectorViewState() {
        return roomSelectorViewState;
    }
}
