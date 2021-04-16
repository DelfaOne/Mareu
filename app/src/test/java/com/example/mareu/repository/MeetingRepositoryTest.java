/*
package com.example.mareu.repository;

import com.example.mareu.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRepositoryTest {

    @Mock
    MeetingApiService apiService;

    MeetingRepository meetingRepository;

    @Before
    public void setup() {
        List<Meeting> meetingList = Arrays.asList(
                new Meeting(1, "Réunion A", "Salle Peach", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
                new Meeting(2, "Réunion B", "Salle Mario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
                new Meeting(3, "Réunion C", "Salle Wario", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
                new Meeting(4, "Réunion D", "Salle Bowser", LocalDateTime.now(), "maxime@lamzone, fadel@foudi"),
                new Meeting(5, "Réunion E", "Salle Luigi", LocalDateTime.now(), "maxime@lamzone, fadel@foudi")
        );

        Mockito.when(apiService.getMeeting()).thenReturn(meetingList);

        meetingRepository = new MeetingRepository(apiService);
    }

    @Test
    public void shouldGetAllDummyMeeting() {
        //WHEN
        List<Meeting> result = meetingRepository.getMeeting();

        //THEN
        assertEquals(5, result.size());
    }

    //TODO
    @Test
    public void shouldAddNewMeetingItem() {
        //GIVEN
        String itemSubject = "Réunion F";
        String itemLocation = "Salle Bowser";
        LocalDateTime itemDate = LocalDateTime.now();
        String itemParticipants = "toto.toto@toto.com";

        //WHEN
        meetingRepository.addMeetingItem(itemSubject, itemLocation, itemDate, itemParticipants);

        //THEN
        Mockito.verify(apiService).addMeeting(any());
        Mockito.verify(apiService).generateNewMeetingId();
        Mockito.verifyNoMoreInteractions(apiService);

    }

    @Test
    public void correctlyGetMeetingByDate() {
        //GIVEN
        LocalDateTime startDate = LocalDateTime.now().minusMonths(1);
        LocalDateTime endDate = LocalDateTime.now().plusMonths(1);
        List<Meeting> meetingList = Arrays.asList(
                new Meeting(
                        1,
                        "Before",
                        "Salle A",
                        startDate.minusMonths(1),
                        "toto.toto@toto.com"
                ),
                new Meeting(
                        2,
                        "Now",
                        "Salle B",
                        LocalDateTime.now(),
                        "toto.toto@toto.com"
                ),
                new Meeting(
                        3,
                        "After",
                        "Salle A",
                        endDate.plusMonths(1),
                        "toto.toto@toto.com"
                )
        );

        Mockito.when(apiService.getMeeting()).thenReturn(meetingList);

        //WHEN
        List<Meeting> result = meetingRepository.getMeetingByDate(startDate.toLocalDate(), endDate.toLocalDate());

        //THEN
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getId());
    }

    @Test
    public void correctlyGetMeetingByLocation() {
        //GIVEN
        String location = "Salle Mario";

        List<Meeting> meetingList = Arrays.asList(
                new Meeting(
                        1,
                        "Before",
                        "Salle Mario",
                        LocalDateTime.now(),
                        "toto.toto@toto.com"
                ),
                new Meeting(
                        2,
                        "Now",
                        "Salle Marion",
                        LocalDateTime.now(),
                        "toto.toto@toto.com"
                ),
                new Meeting(
                        3,
                        "After",
                        "Salle A",
                        LocalDateTime.now(),
                        "toto.toto@toto.com"
                )
        );
        Mockito.when(apiService.getMeeting()).thenReturn(meetingList);

        //WHEN
        List<Meeting> result = meetingRepository.getMeetingByLocation(location);

        //THEN
        assertEquals(2, result.size());
    }
}*/
