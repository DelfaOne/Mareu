package com.example.mareu.addmeeting;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.mareu.repository.MeetingRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class AddMeetingViewModelTest extends TestCase {
    /*Mockito.when(meetingRepository.getMeeting())
                .thenReturn(Arrays.asList());*/
    @Mock
    MeetingRepository meetingRepository;

    AddMeetingViewModel addMeetingViewModel;

    @Mock
    Application application;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        addMeetingViewModel = new AddMeetingViewModel(meetingRepository, application);
    }

    @Test
    public void verifyOnSubjectChange() {
        //GIVEN
        final Observer<AddMeetingViewState> observer = Mockito.mock(Observer.class);
        String subject = "Toto";
        addMeetingViewModel.viewStateLiveData.observeForever(observer);

        //WHEN
        addMeetingViewModel.onSubjectChange(subject);

        //THEN
        final AddMeetingViewState expected = new AddMeetingViewState(
                subject,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Mockito.verify(observer).onChanged(Mockito.argThat(argument -> {
            return argument.getSubject().equals(subject);
        }));
    }


}