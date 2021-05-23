package com.example.mareu.roomselector;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.RoomSelectorItemBinding;

public class RoomSelectorAdapter extends ListAdapter<RoomSelectorViewState, RoomSelectorAdapter.ViewHolder> {

    ViewHolder.OnCheckedListener onCheckedListener;

    public RoomSelectorAdapter(ViewHolder.OnCheckedListener onCheckedListener) {
        super(new DiffUtil.ItemCallback<RoomSelectorViewState>() {
            @Override
            public boolean areItemsTheSame(@NonNull RoomSelectorViewState oldItem, @NonNull RoomSelectorViewState newItem) {
                return oldItem.getRoomName().equalsIgnoreCase(newItem.getRoomName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull RoomSelectorViewState oldItem, @NonNull RoomSelectorViewState newItem) {
                return oldItem.isSelected() == newItem.isSelected();
            }
        });

        this.onCheckedListener = onCheckedListener;
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
        holder.bind(getItem(position), onCheckedListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final RoomSelectorItemBinding vb;


        public ViewHolder(RoomSelectorItemBinding vb) {
            super(vb.getRoot());
            this.vb = vb;
        }

        public void bind(RoomSelectorViewState item, OnCheckedListener onCheckedListener) {
            vb.roomSelectorCheckbox.setOnCheckedChangeListener(null);
            vb.roomSelectorCheckbox.setText(item.getRoomName());
            vb.roomSelectorCheckbox.setSelected(item.isSelected());
            vb.roomSelectorCheckbox.setChecked(item.isSelected());
            vb.roomSelectorCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                onCheckedListener.onChecked(isChecked, item.getRoomName());
            });
        }

        public interface OnCheckedListener {
            void onChecked(boolean checked, String name);
        }
    }
}
