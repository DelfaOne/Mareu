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
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class FragmentAddMeeting extends Fragment {
    private FragmentAddMeetingBinding vb;

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

        vb.dateEdit.setOnClickListener(v ->
                MaterialDatePicker.Builder.datePicker()
                        .build()
                        .addOnPositiveButtonClickListener(epoch -> {
                            meetingViewModel.onDateChange(epoch);
                        })
                        .show(getParentFragmentManager(), "Date Picker"));

        vb.hoursEdit.setOnClickListener(v -> {
            MaterialTimePicker materialTimePicker = new MaterialTimePicker();
            materialTimePicker.addOnPositiveButtonClickListener(v1 -> {
                meetingViewModel.onTimeChange(materialTimePicker.getHour(), materialTimePicker.getMinute());
            });
        });
        vb.addBtn.setOnClickListener(v -> {
           /* meetingViewModel.onSubjectChange(convertEditContent(vb.subjectEdit));
            meetingViewModel.onPlaceChange(vb.locationMenu.getText().toString());
            meetingViewModel.onDateChange(convertEditContent(vb.dateEdit) + "+" +convertEditContent(vb.hoursEdit));
            meetingViewModel.onEmailChange(convertEditContent(vb.mailEdit));*/
            meetingViewModel.onButtonAddClick();
            /*NavHostFragment.findNavController(this).navigate(R.id.action_fragmentAddMeeting_pop_including_fragmentListMeeting2);
            hideKeyboardFrom(getContext(),vb.getRoot());*/
        });
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
