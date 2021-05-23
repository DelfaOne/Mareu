package com.example.mareu.addmeeting;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.mareu.R;
import com.example.mareu.repository.meeting.MeetingRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RunWith(MockitoJUnitRunner.class)
public class AddMeetingViewModelTest extends TestCase {
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
    public void verifyOnSubjectChange() {
        //GIVEN
        String subject = "Toto";


        //WHEN
        addMeetingViewModel.onSubjectChange(subject);
        addMeetingViewModel.viewStateLiveData.observeForever(addMeetingViewState -> {
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
        });
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

    @Test
    public void shouldDisplaySubjectErrorWhenSubjectIsEmpty() {
        //GIVEN
        String emptySubject = null;
        Mockito.doReturn("subjectError").when(application).getString(R.string.error_subject_missing);

        //WHEN
        addMeetingViewModel.onSubjectChange(emptySubject);

        //THEN
        addMeetingViewModel.viewStateLiveData.observeForever(addMeetingViewState -> {
            final AddMeetingViewState expected = new AddMeetingViewState(
                    null,
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

        });
    }

    @Test
    public void shouldDisplayDateErrorWhenDateIsEmpty() {
        //GIVEN
        Long emptyDate = null;
        Mockito.doReturn("dateError").when(application).getString(R.string.error_date_missing);

        //WHEN
        addMeetingViewModel.onDateChange(emptyDate);

        //THEN
        addMeetingViewModel.viewStateLiveData.observeForever(addMeetingViewState -> {
            final AddMeetingViewState expected = new AddMeetingViewState(
                    null,
                    null,
                    null,
                    null,
                    null,
                    "dateError",
                    null,
                    null,
                    null
            );
            //Check state
            assertEquals(expected, addMeetingViewState);
        });
    }

    @Test
    public void shouldDisplayTimeErrorWhenTimeIsEmpty() {
        //GIVEN
        int incorrectHour = 24;
        int incorrectMinute = 60;
        Mockito.doReturn("dateError").when(application).getString(R.string.error_date_missing);

        //WHEN
        addMeetingViewModel.onTimeChange(incorrectHour, incorrectMinute);

        //THEN
        addMeetingViewModel.viewStateLiveData.observeForever(addMeetingViewState -> {
            final AddMeetingViewState expected = new AddMeetingViewState(
                    null,
                    null,
                    null,
                    null,
                    null,
                    "dateError",
                    null,
                    null,
                    null
            );
            //Check state
            assertEquals(expected, addMeetingViewState);
        });
    }

    @Test
    public void shouldDisplayEmailErrorWhenEmailIsEmpty() {
        //GIVEN
        String emptyEmail = null;
        Mockito.doReturn("emailMissing").when(application).getString(R.string.error_email_missing);

        //WHEN
        addMeetingViewModel.onSubjectChange(emptyEmail);

        //THEN
        addMeetingViewModel.viewStateLiveData.observeForever(addMeetingViewState -> {
            final AddMeetingViewState expected = new AddMeetingViewState(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "emailMissing"
            );
            //Check state
            assertEquals(expected, addMeetingViewState);

        });
    }

    @Test
    public void shouldDisplayErrorWhenDateIsBeforeActualDate() {
        //GIVEN
        long incorrectDate = 1612275107000L; //02/02/2021 - 15:11:47
        Mockito.doReturn("dateError").when(application).getString(R.string.error_date_missing);

        //WHEN
        addMeetingViewModel.onDateChange(incorrectDate);
        addMeetingViewModel.onButtonAddClick();

        //THEN
        addMeetingViewModel.viewStateLiveData.observeForever(addMeetingViewState -> {
            final AddMeetingViewState expected = new AddMeetingViewState(
                    null,
                    null,
                    null,
                    null,
                    "02 02 2021",
                    "dateError",
                    null,
                    null,
                    null
            );
            assertEquals(expected, addMeetingViewState);
        });
    }


}