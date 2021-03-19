package com.example.mareu.addmeeting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
    private MaterialDatePicker.Builder builderDate;
    private MaterialDatePicker materialDatePicker;
    private MaterialTimePicker materialTimePicker;
    private MaterialTimePicker.Builder builderTime = new MaterialTimePicker.Builder();

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


        vb.locationMenu.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_item, getMenuAdapter()));

        setDateBuilder();
        setTimeBuilder();

        vb.dateEdit.setOnClickListener(v -> materialDatePicker.show(getParentFragmentManager(), "Date Picker"));
        materialDatePicker.addOnPositiveButtonClickListener(validate -> vb.dateEdit.setText(materialDatePicker.getHeaderText()));

        vb.hoursEdit.setOnClickListener(v -> materialTimePicker.show(getParentFragmentManager(), "Time Picker"));
        materialTimePicker.addOnPositiveButtonClickListener(validate -> vb.dateEdit.setText(materialTimePicker.getHour() + " : " + materialTimePicker.getMinute()));

        vb.addBtn.setOnClickListener( v -> {
            meetingViewModel.onSubjectChange(convertEditContent(vb.subjectEdit));
            meetingViewModel.onPlaceChange(vb.locationMenu.getText().toString());
            meetingViewModel.onDateChange(convertEditContent(vb.dateEdit));
            meetingViewModel.onEmailChange(convertEditContent(vb.mailEdit));
            meetingViewModel.onButtonAddClick();
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentAddMeeting_pop_including_fragmentListMeeting2);
            hideKeyboardFrom(getContext(),vb.getRoot());
        });
    }

    private String convertEditContent(TextInputEditText txt) {
        return txt.getText().toString();
    }

    private void setDateBuilder() {
        builderDate = MaterialDatePicker.Builder.datePicker();
        materialDatePicker = builderDate.build();
    }

    private void setTimeBuilder() {
        materialTimePicker = builderTime.setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Selectionner l'heure")
                .build();
    }

    private String[] getMenuAdapter() {
        String[] locationList = getResources().getStringArray(R.array.location);
        return locationList;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}