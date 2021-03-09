package com.example.mareu.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.R;
import com.example.mareu.databinding.FragmentAddMeetingBinding;
import com.example.mareu.repository.Meeting;
import com.example.mareu.viewmodel.MeetingViewModel;
import com.example.mareu.viewmodel.ViewModelFactory;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class FragmentAddMeeting extends Fragment {
    private FragmentAddMeetingBinding vb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentAddMeetingBinding.inflate(inflater, container, false);
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
        String[] location;
        location=getResources().getStringArray(R.array.location);
        vb.locationMenu.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_item, location));

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker materialDatePicker = builder.build();

        vb.dateEdit.setOnClickListener(v -> materialDatePicker.show(getParentFragmentManager(), "Date Picker"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> vb.dateEdit.setText(materialDatePicker.getHeaderText()));
    }

    private void onAddButtonClicked() {
        MeetingViewModel meetingViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(MeetingViewModel.class);
        vb.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingViewModel.addMeeting(new Meeting(
                        1,
                        vb.subjectEdit.getText().toString(),
                        vb.locationMenu.getText().toString(),
                        vb.dateEdit.get,
                        vb.mailEdit.getText().toString()
                ));
            }
        });
    }
}
