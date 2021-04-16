package com.example.mareu.roomselector;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.ViewModelFactory;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class RoomSelectorDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RoomSelectorViewModel vm = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(RoomSelectorViewModel.class);

        View view = inflater.inflate(R.layout.room_selector_fragment, container, false);
        RecyclerView roomSelectorRecyclerView = view.findViewById(R.id.room_selector_recycler_view);
        RoomSelectorAdapter roomSelectorAdapter = new RoomSelectorAdapter();
        roomSelectorRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        roomSelectorRecyclerView.setAdapter(roomSelectorAdapter);
        vm.getRoomSelectorViewState().observe(
                this,
                roomSelectorViewStates -> roomSelectorAdapter.submitList(roomSelectorViewStates)
        );
        return view;
    }
}
