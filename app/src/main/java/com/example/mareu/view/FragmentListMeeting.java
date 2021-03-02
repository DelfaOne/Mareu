package com.example.mareu.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.databinding.FragmentListMeetingBinding;
import com.example.mareu.viewmodel.MeetingViewModel;
import com.example.mareu.viewmodel.MeetingViewState;
import com.example.mareu.viewmodel.ViewModelFactory;

import java.util.List;

public class FragmentListMeeting extends Fragment {

    private FragmentListMeetingBinding vb;
    private MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentListMeetingBinding.inflate(inflater, container, false);
        init();
        View view = vb.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vb = null;
    }

    public static FragmentListMeeting newInstance() {
        FragmentListMeeting fragment = new FragmentListMeeting();
        return fragment;
    }

    private void init() {
        MeetingViewModel meetingViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(MeetingViewModel.class);

        meetingViewModel.meetingViewStateLiveData.observe(this, meetingViewStates -> meetingRecyclerViewAdapter.submitList(meetingViewStates));

        meetingViewModel.loadData();

        vb.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(new MeetingRecyclerViewAdapter.OnDeleteItem() {
            @Override
            public void deleteItem(MeetingViewState meetingViewState) {
                meetingViewModel.deleteItem(meetingViewState);
            }
        });
        vb.meetingRecyclerview.setAdapter(meetingRecyclerViewAdapter);
    }
}
