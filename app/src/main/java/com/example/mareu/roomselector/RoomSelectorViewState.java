package com.example.mareu.roomselector;

import androidx.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomSelectorViewState that = (RoomSelectorViewState) o;
        return isSelected == that.isSelected &&
                roomName.equals(that.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, isSelected);
    }

    @Override
    public String toString() {
        return "RoomSelectorViewState{" +
                "roomName='" + roomName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
