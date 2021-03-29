package com.example.mareu.addmeeting;

import android.app.Application;
import android.content.res.Resources;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.load.engine.Resource;
import com.example.mareu.R;
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
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    // 26/03/2021 - 19:21:36
    Clock clock = Clock.fixed(Instant.ofEpochMilli(1616782896000L), ZoneOffset.UTC);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        addMeetingViewModel = new AddMeetingViewModel(meetingRepository, application, clock);
    }

    @Test
    public void verifyOnSubjectChange() {
        //GIVEN
        String subject = "Toto";


        //WHEN
        addMeetingViewModel.onSubjectChange(subject);
        addMeetingViewModel.viewStateLiveData.observeForever(new Observer<AddMeetingViewState>() {
            @Override
            public void onChanged(AddMeetingViewState addMeetingViewState) {
                //THEN
                final AddMeetingViewState expected = new AddMeetingViewState(
                        "Toto",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                assertEquals(expected, addMeetingViewState);
            }
        });
    }

    @Test
    public void shouldDisplayErrorOnEmptySubject() {
        //GIVEN
        String subjectEmpty = "";
        Resources mockResources = Mockito.mock(Resources.class);
        Mockito.doReturn("subjectError").when(mockResources).getString(R.string.error_subject_missing);
        Mockito.doReturn(mockResources).when(application).getResources();

        //WHEN
        addMeetingViewModel.onSubjectChange(subjectEmpty);
        addMeetingViewModel.onButtonAddClick();
        addMeetingViewModel.viewStateLiveData.observeForever(new Observer<AddMeetingViewState>() {
            @Override
            public void onChanged(AddMeetingViewState addMeetingViewState) {
                //THEN
                final AddMeetingViewState expected = new AddMeetingViewState(
                        "",
                        "subjectError",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                //Check state
                assertEquals(expected, addMeetingViewState);

            }
        });
    }

    @Test
    public void nominalCase_addNewMeeting() {
        //GIVEN
        String subject = "subject";
        String location = "location";
        long date = 1616782896000L; // 26/03/2021 - 19:21:36
        int hour = 22;
        int min = 30;
        String email = "email";

        //WHEN
        addMeetingViewModel.onSubjectChange(subject);
        addMeetingViewModel.onPlaceChange(location);
        addMeetingViewModel.onDateChange(date);
        addMeetingViewModel.onTimeChange(hour, min);
        addMeetingViewModel.onEmailChange(email);

        addMeetingViewModel.onButtonAddClick();

        //THEN
        Mockito.verify(meetingRepository).addMeetingItem(
                subject,
                location,
                LocalDateTime.of(2021, 3, 26, 22, 30, 0),
                email
        );
        Mockito.verifyNoMoreInteractions(meetingRepository);
    }

    @Test
    public void verifyOnLocationChange() {
        //GIVEN
        final Observer<AddMeetingViewState> observer = Mockito.mock(Observer.class);
        String location = "Salle Mario";
        addMeetingViewModel.viewStateLiveData.observeForever(observer);

        //WHEN
        addMeetingViewModel.onPlaceChange(location);

        //THEN
        Mockito.verify(observer).onChanged(Mockito.argThat(argument -> {
            return argument.getLocation().equals(location);
        }));
    }

    @Test
    public void verifyOnDateChange() {
        //GIVEN
        final Observer<AddMeetingViewState> observer = Mockito.mock(Observer.class);
        long date = 161602;
        String readableDate = "18 mars 2021";
        addMeetingViewModel.viewStateLiveData.observeForever(observer);

        //WHEN
        addMeetingViewModel.onDateChange(date);

        //THEN
        Mockito.verify(observer).onChanged(Mockito.argThat(argument -> {
            return argument.getDate().equals(readableDate);
        }));
    }

    @Test
    public void verifyOnTimeChange() {
        //GIVEN
        final Observer<AddMeetingViewState> observer = Mockito.mock(Observer.class);
        int hour = 22;
        int minute = 30;
        String readableTime = "22:30";
        addMeetingViewModel.viewStateLiveData.observeForever(observer);

        //WHEN
        addMeetingViewModel.onTimeChange(hour, minute);

        //THEN
        Mockito.verify(observer).onChanged(Mockito.argThat(argument -> {
            return argument.getTime().equals(readableTime);
        }));
    }

    @Test
    public void verifyOnEmailChange() {
        //GIVEN
        final Observer<AddMeetingViewState> observer = Mockito.mock(Observer.class);
        String email = "toto.toto@gmail.com";
        addMeetingViewModel.viewStateLiveData.observeForever(observer);

        //WHEN
        addMeetingViewModel.onEmailChange(email);

        //THEN
        Mockito.verify(observer).onChanged(Mockito.argThat(argument -> {
            return argument.getEmail().equals(email);
        }));
    }



}