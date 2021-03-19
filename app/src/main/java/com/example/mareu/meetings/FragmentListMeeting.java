package com.example.mareu.meetings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.R;
import com.example.mareu.ViewModelFactory;
import com.example.mareu.databinding.FragmentListMeetingBinding;

import java.util.List;

public class FragmentListMeeting extends Fragment {

    private FragmentListMeetingBinding vb;
    private MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentListMeetingBinding.inflate(inflater, container, false);
        init();
        setHasOptionsMenu(true);

        View view = vb.getRoot();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter_date : {
                Log.v("FirstItem: ", "0 trigger");
                return true;
            }
            case R.id.menu_filter_room : {
                Log.v("SecondItem: ", "1 trigger");
                return true;
            }

            default: return super.onOptionsItemSelected(item);
        }
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

        //si meetingViewStates change alors on met à jour notre adapter
        meetingViewModel.meetingViewStateLiveData.observe(this, meetingViewStates -> meetingRecyclerViewAdapter.submitList(meetingViewStates));

        meetingViewModel.loadData();

        vb.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(meetingViewState -> meetingViewModel.deleteItem(meetingViewState));

        vb.meetingRecyclerview.setAdapter(meetingRecyclerViewAdapter);

        vb.addNeighbourBtn.setOnClickListener(v -> { Navigation.findNavController(v).navigate(R.id.action_fragmentListMeeting2_to_fragmentAddMeeting);
        });
    }
}
