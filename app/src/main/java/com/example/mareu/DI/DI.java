package com.example.mareu.DI;

import com.example.mareu.data.DummyMeetingDataStore;
import com.example.mareu.data.MeetingDataStore;
import com.example.mareu.repository.room.RoomRepository;

/**
 * Dependency injector to get instance of services
 */
public class DI {
    //TODO type DummyNeighbourApiService
    private static final MeetingDataStore sMeetingDataStore = new DummyMeetingDataStore();
    private static final RoomRepository sRoomRepository = new RoomRepository();

    public static MeetingDataStore getNeighbourApiService() {
        return sMeetingDataStore;
    }

    public static RoomRepository getRoomRepository() {
        return sRoomRepository;
    }
}
