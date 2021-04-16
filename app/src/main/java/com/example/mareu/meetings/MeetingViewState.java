package com.example.mareu.meetings;

import android.graphics.Color;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

public class MeetingViewState {

    private final int id;
    @NonNull
    private final String title;
    @NonNull
    private final String roomName;
    @NonNull
    private final String participants;
    @ColorRes
    private final int avatarColor;

    public MeetingViewState(int id, @NonNull String title, @NonNull String roomName, @NonNull String participants, @ColorRes int avatarColor) {
        this.id = id;
        this.title = title;
        this.roomName = roomName;
        this.participants = participants;
        this.avatarColor = avatarColor;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    @NonNull
    public String getParticipants() {
        return participants;
    }

    public int getAvatarColor() {
        return avatarColor;
    }
}
