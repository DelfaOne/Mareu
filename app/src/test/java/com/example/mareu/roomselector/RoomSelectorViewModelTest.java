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
    public void verifyOnCheckedChange() throws InterruptedException {
        roomSelectorViewModel = setupRoomSelectorViewModel();
        //GIVEN
        String roomName = "Peach";
        boolean isChecked = true;

        //WHEN

        roomSelectorViewModel.onCheckedChange(isChecked, roomName);
        List<RoomSelectorViewState> result = getOrAwaitValue(roomSelectorViewModel.getRoomSelectorViewStateLiveData());

        assertEquals(
                new RoomSelectorViewState(roomName, isChecked),
                result.get(0)
        );
    }

    private RoomSelectorViewModel setupRoomSelectorViewModel() {
        return new RoomSelectorViewModel(
                roomRepository
        );
    }

}