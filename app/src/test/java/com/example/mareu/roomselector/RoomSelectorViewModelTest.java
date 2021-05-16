package com.example.mareu.roomselector;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.mareu.data.DummyMeetingGenerator;
import com.example.mareu.repository.room.RoomRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mareu.meetings.UnitTestUtils.getOrAwaitValue;

@RunWith(MockitoJUnitRunner.class)
public class RoomSelectorViewModelTest extends TestCase {

    RoomSelectorViewModel roomSelectorViewModel;

    @Mock
    RoomRepository roomRepository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MutableLiveData<Map<String, Boolean>> roomCheckedLiveData;

    @Before
    public void setup() {
        roomCheckedLiveData = new MutableLiveData<>();
        roomCheckedLiveData.setValue(new HashMap<>(DummyMeetingGenerator.getRooms()));
        Mockito.doReturn(roomCheckedLiveData).when(roomRepository).getRoomsLiveData();

    }

    @Test
    public void nominalCaseOnCheckedChange() throws InterruptedException {
        roomSelectorViewModel = setupRoomSelectorViewModel();
        //GIVEN
        String roomName = "Peach";
        boolean isChecked = true;

        //WHEN

        roomSelectorViewModel.onCheckedChange(isChecked, roomName);
        List<RoomSelectorViewState> result = getOrAwaitValue(roomSelectorViewModel.getRoomSelectorViewStateLiveData());

        List<RoomSelectorViewState> expected = new ArrayList<>();

        expected.add(new RoomSelectorViewState("Bowser", false));
        expected.add(new RoomSelectorViewState("Luigi", false));
        expected.add(new RoomSelectorViewState("Mario", false));
        expected.add(new RoomSelectorViewState("Peach", false));
        expected.add(new RoomSelectorViewState("Wario", false));

        assertEquals(expected, result);

        Mockito.verify(roomRepository).getRoomsLiveData();
        Mockito.verify(roomRepository).toggleRoomChecked(isChecked, roomName);
    }

    @Test
    public void OnCheckedChange() throws InterruptedException {
        //GIVEN
        HashMap<String, Boolean> roomCheckedMap = new HashMap<>(DummyMeetingGenerator.getRooms());
        roomCheckedMap.put("Peach", true);

        roomCheckedLiveData.setValue(roomCheckedMap);
        roomSelectorViewModel = setupRoomSelectorViewModel();

        //WHEN
        List<RoomSelectorViewState> result = getOrAwaitValue(roomSelectorViewModel.getRoomSelectorViewStateLiveData());

        List<RoomSelectorViewState> expected = new ArrayList<>();

        expected.add(new RoomSelectorViewState("Bowser", false));
        expected.add(new RoomSelectorViewState("Luigi", false));
        expected.add(new RoomSelectorViewState("Mario", false));
        expected.add(new RoomSelectorViewState("Peach", true));
        expected.add(new RoomSelectorViewState("Wario", false));

        assertEquals(expected, result);
    }

    private RoomSelectorViewModel setupRoomSelectorViewModel() {
        return new RoomSelectorViewModel(
                roomRepository
        );
    }

}