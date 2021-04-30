package com.example.mareu.meetings;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Color;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.R;
import com.example.mareu.repository.meeting.Meeting;
import com.example.mareu.repository.meeting.MeetingRepository;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.mareu.meetings.UnitTestUtils.getOrAwaitValue;

@RunWith(MockitoJUnitRunner.class)
public class MeetingViewModelTest extends TestCase {
    @Mock
    MeetingRepository meetingRepository;

    @Mock
    Application application;

    private MutableLiveData<List<Meeting>> meetingsLiveData;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {

        meetingsLiveData = new MutableLiveData<>();
        Mockito.doReturn(meetingsLiveData).when(meetingRepository).getMeetings();

    }

    @Test
    public void whenDeleteItemClickedShouldCallMeetingRepository() {
        //GIVEN
        MeetingViewModel meetingViewModel = new MeetingViewModel(meetingRepository, application);

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
        MeetingViewModel meetingViewModel = new MeetingViewModel(meetingRepository, application);
        androidx.core.util.Pair<Long, Long> dateRage = Pair.create(1617235200000L, 1617840000000L); // 1 Avril 2021 au 8 Avril 2021

        //WHEN
       meetingViewModel.onDateRangeSelected(dateRage);
       List<MeetingViewState> result = getOrAwaitValue(meetingViewModel.getMeetingViewStateLiveData());

        //THEN
        assertEquals(
                Arrays.asList(
                        new MeetingViewState(
                                1,
                                "toto",
                                "toto",
                                "toto",
                                Color.blue(1)
                        )
                ),
                result
        );

    }

    @Test
    public void shouldReturnCorrectRoomColor() {
        //GIVEN
        MeetingViewModel meetingViewModel = new MeetingViewModel(meetingRepository, application);
        String roomName = "Peach";

        //WHEN
        meetingViewModel.getColor(roomName);

        //THEN
    }

}