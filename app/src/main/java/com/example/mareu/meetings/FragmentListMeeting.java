package com.example.mareu.meetings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.R;
import com.example.mareu.ViewModelFactory;
import com.example.mareu.databinding.FragmentListMeetingBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class FragmentListMeeting extends Fragment {

    private FragmentListMeetingBinding vb;
    private MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;

    private MeetingViewModel meetingViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentListMeetingBinding.inflate(inflater, container, false);
        meetingViewModel= new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingViewModel.class);
        init();
        setHasOptionsMenu(true);
        View view = vb.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter_date : {
                MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().build();
                materialDatePicker.addOnPositiveButtonClickListener(selection -> meetingViewModel.onDateRangeSelected(selection));
                materialDatePicker.show(getParentFragmentManager(), "Date Picker");
                break;
            }
            case R.id.menu_filter_room : {
                break;
            }
            case R.id.menu_show_all : {
                meetingViewModel.onDateSortingButtonSelected();
                break;
            }
            default: return super.onOptionsItemSelected(item);
        }
        return true;
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
        //si meetingViewStates change alors on met à jour notre adapter
        meetingViewModel.getMeetingViewStateLiveData().observe(this, meetingViewStates -> meetingRecyclerViewAdapter.submitList(meetingViewStates));

        vb.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(meetingViewModel::deleteItem);

        vb.meetingRecyclerview.setAdapter(meetingRecyclerViewAdapter);

        vb.addNeighbourBtn.setOnClickListener(v -> { Navigation.findNavController(v).navigate(R.id.action_fragmentListMeeting2_to_fragmentAddMeeting);
        });
    }
}
