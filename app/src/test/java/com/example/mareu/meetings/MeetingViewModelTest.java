package com.example.mareu.meetings;

import android.app.Application;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.R;
import com.example.mareu.data.DummyMeetingGenerator;
import com.example.mareu.repository.meeting.Meeting;
import com.example.mareu.repository.meeting.MeetingRepository;
import com.example.mareu.repository.room.RoomRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mareu.meetings.UnitTestUtils.getOrAwaitValue;

@RunWith(MockitoJUnitRunner.class)
public class MeetingViewModelTest extends TestCase {
    @Mock
    MeetingRepository meetingRepository;

    @Mock
    Application application;

    @Mock
    RoomRepository roomRepository;

    private MutableLiveData<List<Meeting>> meetingsLiveData;

    private MutableLiveData<Map<String, Boolean>> roomCheckedLiveData;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {

        meetingsLiveData = new MutableLiveData<>();
        roomCheckedLiveData = new MutableLiveData<>();
        meetingsLiveData.setValue(DummyMeetingGenerator.getDummyMeeting());
        roomCheckedLiveData.setValue(new HashMap<>());
        Mockito.doReturn(meetingsLiveData).when(meetingRepository).getMeetings();
        Mockito.doReturn(roomCheckedLiveData).when(roomRepository).getRoomsLiveData();
    }

    @Test
    public void whenDeleteItemClickedShouldCallMeetingRepository() {
        //GIVEN
        MeetingViewModel meetingViewModel = setupViewModel();

        //WHEN
        meetingViewModel.deleteItem(1);

        //THEN
        Mockito.verify(meetingRepository, Mockito.times(1)).deleteMeetingItem(1);
        Mockito.verify(meetingRepository, Mockito.times(1)).getMeetings();
        Mockito.verifyNoMoreInteractions(meetingRepository);
    }

    @Test
    public void whenDateRangeSelectedShouldCorrectlyDisplayMeetingsByRange() throws InterruptedException {
        //GIVEN
        MeetingViewModel meetingViewModel = setupViewModel();
        androidx.core.util.Pair<Long, Long> dateRage = Pair.create(1617235200000L, 1617317631000L); // 1 Avril 2021 au 8 Avril 2021
        Mockito.doReturn("meetingTitle").when(application).getString(Mockito.eq(R.string.meeting_title), Mockito.any(), Mockito.any());
        Mockito.doReturn("roomName").when(application).getString(Mockito.eq(R.string.room_name), Mockito.any());


        //WHEN
        meetingViewModel.onDateRangeSelected(dateRage);
        List<MeetingViewState> result = getOrAwaitValue(meetingViewModel.getMeetingViewStateLiveData());

        //THEN
        assertEquals(
                Arrays.asList(
                        new MeetingViewState(
                                0,
                                "meetingTitle",
                                "roomName",
                                "maxime@lamzone, fadel@foudi",
                                R.color.Peach
                        )
                ),
                result
        );

    }

    @Test
    public void shouldReturnCorrectRoomColor() {
        //GIVEN
        MeetingViewModel meetingViewModel = setupViewModel();
        String roomName = "Peach";

        //WHEN
        int actual = meetingViewModel.getColor(roomName);
        int excepted = 2131099651;

        //THEN
        assertEquals(actual, excepted);
    }

    private MeetingViewModel setupViewModel() {
        return new MeetingViewModel(
                application,
                meetingRepository,
                roomRepository
        );
    }

}