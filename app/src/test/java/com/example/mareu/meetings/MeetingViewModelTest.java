package com.example.mareu.meetings;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mareu.addmeeting.AddMeetingViewModel;
import com.example.mareu.addmeeting.AddMeetingViewState;
import com.example.mareu.repository.Meeting;
import com.example.mareu.repository.MeetingRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class MeetingViewModelTest extends TestCase {
    @Mock
    MeetingRepository meetingRepository;

    MeetingViewModel meetingViewModel;

    private MutableLiveData<List<MeetingViewState>> _meetingViewStateLiveData;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        meetingViewModel = new MeetingViewModel(meetingRepository);

        _meetingViewStateLiveData = new MutableLiveData<>();
    }

    @Test
    public void verifyCorrectlyLoadData() {
        //GIVEN

        //WHEN

        //THEN
    }

}