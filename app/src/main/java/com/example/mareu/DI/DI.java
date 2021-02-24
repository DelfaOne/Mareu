package com.example.mareu.DI;

import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingApiService;
/**
 * Dependency injector to get instance of services
 */
public class DI {
    //TODO type DummyNeighbourApiService
    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getNeighbourApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
