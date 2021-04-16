package com.example.mareu.meetings;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.repository.meeting.Meeting;
import com.example.mareu.repository.meeting.MeetingRepository;
import com.example.mareu.service.MeetingApiService;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

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
        //meetingViewModel = new MeetingViewModel(meetingRepository, MainApplication.getsApplication());

        meetingsLiveData = new MutableLiveData<>();
        Mockito.doReturn(meetingsLiveData).when(meetingRepository).getMeetings();

    }

    @Test
    public void when_deleteItemClicked_should_call_meeting_repository() {
        //GIVEN
        MeetingViewModel meetingViewModel = new MeetingViewModel(meetingRepository, application);

        //WHEN
        meetingViewModel.deleteItem(1);

        //THEN
        Mockito.verify(meetingRepository, Mockito.times(1)).deleteMeetingItem(1);
        Mockito.verify(meetingRepository, Mockito.times(1)).getMeetings();
        Mockito.verifyNoMoreInteractions(meetingRepository);
    }

}