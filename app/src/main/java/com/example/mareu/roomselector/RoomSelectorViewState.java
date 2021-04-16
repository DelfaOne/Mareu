package com.example.mareu.roomselector;

import androidx.annotation.NonNull;

public class RoomSelectorViewState {
    @NonNull
    private final String roomName;

    private final boolean isSelected;

    public RoomSelectorViewState(@NonNull String roomName, boolean isSelected) {
        this.roomName = roomName;
        this.isSelected = isSelected;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
