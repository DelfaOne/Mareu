package com.example.mareu.addmeeting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mareu.R;
import com.example.mareu.databinding.FragmentAddMeetingBinding;
import com.example.mareu.ViewModelFactory;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class FragmentAddMeeting extends Fragment {
    private FragmentAddMeetingBinding vb;
    private boolean isRefreshing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentAddMeetingBinding.inflate(inflater, container, false);
        init();
        View view = vb.getRoot();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vb = null;
    }

    private void init() {
        AddMeetingViewModel meetingViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(AddMeetingViewModel.class);

        //Subject
        vb.subjectEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isRefreshing) {
                    meetingViewModel.onSubjectChange(s.toString());
                }
            }
        });

        //Location
        vb.locationMenu.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_item, getMenuAdapter()));
        vb.locationMenu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isRefreshing) {
                    meetingViewModel.onPlaceChange(s.toString());
                }
            }
        });

        vb.dateEdit.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().build();
            materialDatePicker.addOnPositiveButtonClickListener(epoch -> { meetingViewModel.onDateChange(epoch); });
            materialDatePicker.show(getParentFragmentManager(), "Date Picker");
        });

        vb.timeEdit.setOnClickListener(v -> {
            MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
            MaterialTimePicker materialTimePicker = builder.setTimeFormat(TimeFormat.CLOCK_24H)
                    .build();
            materialTimePicker.show(getParentFragmentManager(), "Time Picker");

            materialTimePicker.addOnPositiveButtonClickListener(v1 -> {
                meetingViewModel.onTimeChange(materialTimePicker.getHour(), materialTimePicker.getMinute());
            });
        });



        meetingViewModel.viewStateLiveData.observe(this, this::setMeetingViewState);


        vb.addBtn.setOnClickListener(v -> {
           meetingViewModel.onButtonAddClick();
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentAddMeeting_pop_including_fragmentListMeeting2);
            hideKeyboardFrom(getContext(),vb.getRoot());
        });
    }

    private void setMeetingViewState(AddMeetingViewState addMeetingViewState) {
        isRefreshing = true;
        setTextOnEditText(addMeetingViewState.getSubject(), vb.subjectEdit);
        setTextOnEditText(addMeetingViewState.getDate(), vb.dateEdit);
        setTextOnEditText(addMeetingViewState.getTime(), vb.timeEdit);
        setTextOnEditText(addMeetingViewState.getEmail(), vb.mailEdit);

        vb.textFieldSubject.setError(addMeetingViewState.getSubjectError());
        vb.locationMenu.setText(addMeetingViewState.getLocation(), false);
        vb.textFieldLocation.setError(addMeetingViewState.getLocationError());

        isRefreshing = false;
    }

    private String[] getMenuAdapter() {
        String[] locationList = getResources().getStringArray(R.array.location);
        return locationList;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setTextOnEditText(String text, TextInputEditText on) {
        if (text == null) {
            on.setText("");
            on.setSelection(0);
        } else {
            on.setText(text);
            on.setSelection(text.length());
        }
    }
}
