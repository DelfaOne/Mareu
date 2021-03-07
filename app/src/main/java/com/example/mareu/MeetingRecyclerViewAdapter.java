package com.example.mareu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ItemListMeetingBinding;
import com.example.mareu.viewmodel.MeetingViewState;

import java.util.ArrayList;
import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final ArrayList<MeetingViewState> meetingList = new ArrayList<MeetingViewState>();
    private final OnDeleteItem onDeleteItem;


    public MeetingRecyclerViewAdapter(@NonNull OnDeleteItem onDeleteItem) {
        this.onDeleteItem = onDeleteItem;
    }

    public void submitList(List<MeetingViewState> items) {
        meetingList.clear();
        meetingList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListMeetingBinding itemListMeetingBinding = ItemListMeetingBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new MeetingViewHolder(itemListMeetingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        MeetingViewState meeting = meetingList.get(position);
        holder.itemBinding.meetingTitle.setText(meeting.getReunionSubject() + " - " + meeting.getDate() + " - " + meeting.getLieu());
        holder.itemBinding.meetingParticipants.setText(meeting.getParticipants());
        holder.itemBinding.meetingDeleteBtn.setOnClickListener(v -> onDeleteItem.deleteItem(meeting));
    }

    public interface OnDeleteItem {
        void deleteItem(MeetingViewState meetingViewState);
    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    public MeetingViewState getMeetingAt(int position) {
        return meetingList.get(position);
    }

    static class MeetingViewHolder extends RecyclerView.ViewHolder {

        private ItemListMeetingBinding itemBinding;

        public MeetingViewHolder(ItemListMeetingBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
