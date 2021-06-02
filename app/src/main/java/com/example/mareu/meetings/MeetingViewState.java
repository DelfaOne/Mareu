package com.example.mareu.meetings;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewState that = (MeetingViewState) o;
        return id == that.id &&
                avatarColor == that.avatarColor &&
                title.equals(that.title) &&
                roomName.equals(that.roomName) &&
                participants.equals(that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, roomName, participants, avatarColor);
    }

    @Override
    public String toString() {
        return "MeetingViewState{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", roomName='" + roomName + '\'' +
                ", participants='" + participants + '\'' +
                ", avatarColor=" + avatarColor +
                '}';
    }
}
