package com.example.mareu;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mareu.DI.DI;
import com.example.mareu.addmeeting.AddMeetingViewModel;
import com.example.mareu.meetings.MeetingViewModel;
import com.example.mareu.repository.meeting.MeetingRepository;
import com.example.mareu.repository.room.RoomRepository;
import com.example.mareu.roomselector.RoomSelectorViewModel;

import java.time.Clock;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;
    private final MeetingRepository meetingRepository;
    private final RoomRepository roomRepository;
    private final Clock clock;

    private ViewModelFactory(MeetingRepository meetingRepository, Clock clock, RoomRepository roomRepository) {
        this.meetingRepository = meetingRepository;
        this.clock = clock;
        this.roomRepository = roomRepository;
    }

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                        new MeetingRepository(DI.getNeighbourApiService()),
                        Clock.systemDefaultZone(),
                        DI.getRoomRepository()
                    );
                }
            }
        }
        return factory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeetingViewModel.class)) {
            return (T) new MeetingViewModel(MainApplication.getsApplication(), meetingRepository, roomRepository);
        } else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(meetingRepository, MainApplication.getsApplication(), clock);
        } else if (modelClass.isAssignableFrom(RoomSelectorViewModel.class)) {
            return (T) new RoomSelectorViewModel(roomRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
