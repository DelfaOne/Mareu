package com.example.mareu.meetings;

import android.os.Bundle;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentListMeeting extends Fragment {

    private FragmentListMeetingBinding vb;
    private MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;
    private MeetingViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentListMeetingBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingViewModel.class);
        setupView();
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
                onDateFilterSelected();
                break;
            }
            case R.id.menu_filter_room : {
                onRoomFilterSelected();
                break;
            }
            case R.id.menu_show_all : {
                vm.onDateSortingButtonSelected();
                break;
            }
            default: return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void onDateFilterSelected() {
        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().build();
        materialDatePicker.addOnPositiveButtonClickListener(selection -> vm.onDateRangeSelected(selection));
        materialDatePicker.show(getParentFragmentManager(), "Date Picker");
    }

    private void onRoomFilterSelected() {
        boolean[] isCheckedList = {false, false, false, false, false};
        String[] locationList = getResources().getStringArray(R.array.location);
        List<String> result = new ArrayList<>();

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
        materialAlertDialogBuilder.setTitle(getString(R.string.select_location))
                .setNeutralButton(getString(R.string.negative_button), (dialog, which) -> {
                    dialog.dismiss();
                })
                .setMultiChoiceItems(locationList, null, (dialog, which, isChecked) -> isCheckedList[which] = isChecked)
                .setPositiveButton(getString(R.string.positive_button), (dialog, which) -> {
                    for (int i = 0; i < locationList.length; i++) {
                        if (isCheckedList[i]) {
                            result.add(locationList[i]);
                        }
                    }
                    vm.onLocationChoiceSelected(result);
                })
                .show();
    }

    private void setupView() {
        vm.getMeetingViewStateLiveData().observe(this, meetingViewStates -> meetingRecyclerViewAdapter.submitList(meetingViewStates));

        vb.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(vm::deleteItem);

        vb.meetingRecyclerview.setAdapter(meetingRecyclerViewAdapter);

        vb.addNeighbourBtn.setOnClickListener(v -> { Navigation.findNavController(v).navigate(R.id.action_fragmentListMeeting2_to_fragmentAddMeeting);
        });
    }
}
