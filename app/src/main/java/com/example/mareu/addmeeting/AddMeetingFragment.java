package com.example.mareu.addmeeting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mareu.R;
import com.example.mareu.databinding.FragmentAddMeetingBinding;
import com.example.mareu.ViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class AddMeetingFragment extends BottomSheetDialogFragment {
    private FragmentAddMeetingBinding vb;
    private boolean isRefreshing;
    private AddMeetingViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vb = FragmentAddMeetingBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);
        init();
        View view = vb.getRoot();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vb = null;
    }

    private void init() {
        setupObserver();
        setupView();
    }

    private void setupObserver() {
        vm.viewStateLiveData.observe(getViewLifecycleOwner(), this::setMeetingViewState);
        vm.canAdd.observe(getViewLifecycleOwner(), this::showAddButton);
    }

    private void showAddButton(Boolean isClickable) {
            vb.addBtn.setEnabled(isClickable);

    }

    private void setupView() {
        //Subject
        setTextChange(vb.subjectEdit,vm);

        //Location
        vb.locationMenu.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.list_item, getResources().getStringArray(R.array.location)));
        setTextChange(vb.locationMenu, vm);


        //Date
        vb.dateEdit.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().build();
            materialDatePicker.addOnPositiveButtonClickListener(vm::onDateChange);
            materialDatePicker.show(getParentFragmentManager(), "Date Picker");
        });

        //Time
        vb.timeEdit.setOnClickListener(v -> {
            MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
            MaterialTimePicker materialTimePicker = builder.setTimeFormat(TimeFormat.CLOCK_24H)
                    .build();
            materialTimePicker.show(getParentFragmentManager(), "Time Picker");

            materialTimePicker.addOnPositiveButtonClickListener(v1 -> {
                vm.onTimeChange(materialTimePicker.getHour(), materialTimePicker.getMinute());
            });
        });

        //Mail
        setTextChange(vb.mailEdit, vm);

        //AddButton
        vb.addBtn.setOnClickListener(v -> {
            vm.onButtonAddClick();
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentAddMeeting_pop_including_fragmentListMeeting2);
        });
    }

    private void setMeetingViewState(AddMeetingViewState addMeetingViewState) {
        isRefreshing = true;
        setTextOnEditText(addMeetingViewState.getSubject(), vb.subjectEdit);
        setTextOnEditText(addMeetingViewState.getDate(), vb.dateEdit);
        setTextOnEditText(addMeetingViewState.getTime(), vb.timeEdit);
        setTextOnEditText(addMeetingViewState.getEmail(), vb.mailEdit);
        vb.locationMenu.setText(addMeetingViewState.getLocation(), false);

        setErrorOnField(addMeetingViewState.getSubjectError(), vb.textFieldSubject);
        setErrorOnField(addMeetingViewState.getLocationError(), vb.textFieldLocation);
        setErrorOnField(addMeetingViewState.getDateError(), vb.textFieldDate);
        setErrorOnField(addMeetingViewState.getDateError(), vb.textFieldTime);
        setErrorOnField(addMeetingViewState.getEmailError(), vb.textFieldMail);

        isRefreshing = false;
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

    private void setErrorOnField(String error, TextInputLayout textInputLayout) {
        textInputLayout.setError(error);
    }

    private void setTextChange(EditText editText, AddMeetingViewModel addMeetingViewModel) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isRefreshing) {
                    if (vb.subjectEdit.equals(editText)) {
                        addMeetingViewModel.onSubjectChange(s.toString());
                    } else if (vb.locationMenu.equals(editText)) {
                        addMeetingViewModel.onPlaceChange(s.toString());
                    } else if (vb.mailEdit.equals(editText)) {
                        addMeetingViewModel.onEmailChange(s.toString());
                    }
                }
            }
        });
    }
}
