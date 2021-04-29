package com.example.mareu.roomselector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.ViewModelFactory;
import com.example.mareu.databinding.RoomSelectorFragmentBinding;

public class RoomSelectorDialogFragment extends DialogFragment {

    private RoomSelectorFragmentBinding vb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RoomSelectorViewModel vm = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(RoomSelectorViewModel.class);

        vb = RoomSelectorFragmentBinding.inflate(getLayoutInflater());

        RoomSelectorAdapter roomSelectorAdapter = new RoomSelectorAdapter();
        vb.roomSelectorRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        vb.roomSelectorRecyclerView.setAdapter(roomSelectorAdapter);

        vm.getRoomSelectorViewStateLiveData().observe(
                this, roomSelectorViewStates -> roomSelectorAdapter.submitList(roomSelectorViewStates)
        );
        return vb.getRoot();
    }
}
