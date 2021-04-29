package com.example.mareu.roomselector;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.MeetingRecyclerViewAdapter;
import com.example.mareu.databinding.ItemListMeetingBinding;
import com.example.mareu.databinding.RoomSelectorItemBinding;

public class RoomSelectorAdapter extends ListAdapter<RoomSelectorViewState, RoomSelectorAdapter.ViewHolder> {

    public RoomSelectorAdapter() {
        super(new DiffUtil.ItemCallback<RoomSelectorViewState>() {
            @Override
            public boolean areItemsTheSame(@NonNull RoomSelectorViewState oldItem, @NonNull RoomSelectorViewState newItem) {
                return oldItem.getRoomName().equalsIgnoreCase(newItem.getRoomName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull RoomSelectorViewState oldItem, @NonNull RoomSelectorViewState newItem) {
                return oldItem.isSelected() == newItem.isSelected();            }
        });


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomSelectorItemBinding itemListMeetingBinding = RoomSelectorItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new RoomSelectorAdapter.ViewHolder(itemListMeetingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final RoomSelectorItemBinding vb;

        public ViewHolder(RoomSelectorItemBinding vb) {
            super(vb.getRoot());
            this.vb = vb;
        }

        public void bind(RoomSelectorViewState item) {
            vb.roomSelectorCheckbox.setText(item.getRoomName());
            vb.roomSelectorCheckbox.setSelected(item.isSelected());
            vb.roomSelectorCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                //TODO ViewModel
            });
        }
    }

    public interface CheckedListener {

        void onCheckedChange();
    }
}
