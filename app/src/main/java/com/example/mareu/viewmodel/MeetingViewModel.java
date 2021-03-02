package com.example.mareu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.repository.Meeting;
import com.example.mareu.repository.SingleStringRepo;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private final SingleStringRepo singleStringRepo;
    private final MutableLiveData<List<MeetingViewState>> _meetingViewStateLiveData = new MutableLiveData<>();
    public final LiveData<List<MeetingViewState>> meetingViewStateLiveData = _meetingViewStateLiveData;

    public MeetingViewModel(SingleStringRepo singleStringRepo) {
        this.singleStringRepo = singleStringRepo;
    }

    public void loadData() {
        List<Meeting> meetingList = singleStringRepo.getMeeting();
        List<MeetingViewState> viewState = map(meetingList);
        _meetingViewStateLiveData.setValue(viewState);
    }

    private List<MeetingViewState> map(List<Meeting> meetings) {
        List<MeetingViewState> results = new ArrayList<>();
        for (Meeting meeting : meetings) {
            results.add(new MeetingViewState(
                    meeting.getId(),
                    meeting.getReunionSubject(),
                    meeting.getLieu(),
                    meeting.getDate().toString(),
                    meeting.getParticipants()
            ));
        }
        return results;
    }

    public void deleteItem(MeetingViewState meetingViewState) {
        singleStringRepo.deleteMeetingItem(meetingViewState);
        loadData();
    }
}
