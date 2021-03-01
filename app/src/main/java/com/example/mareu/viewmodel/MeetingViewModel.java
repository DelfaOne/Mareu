package com.example.mareu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.repository.Meeting;
import com.example.mareu.repository.SingleStringRepo;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private final SingleStringRepo singleStringRepo;

    public MeetingViewModel(SingleStringRepo singleStringRepo) {
        this.singleStringRepo = singleStringRepo;
    }

    public LiveData<List<MeetingViewState>> getViewStateLiveData() {
        return Transformations.map(singleStringRepo.getCurrentStringLiveData(), meetings ->
                map(meetings));
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

    public void fillFakeData() {
        singleStringRepo.fillData();
    }

}
