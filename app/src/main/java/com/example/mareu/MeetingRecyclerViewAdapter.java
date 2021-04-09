package com.example.mareu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ItemListMeetingBinding;
import com.example.mareu.meetings.MeetingViewState;

import java.util.ArrayList;
import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final ArrayList<MeetingViewState> meetingList = new ArrayList<>();
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
        holder.itemBinding.meetingTitle.setText(meeting.getReunionSubject() + " - " + meeting.getDate());
        holder.itemBinding.meetingParticipants.setText(meeting.getParticipants());
        holder.itemBinding.meetingLocation.setText("Salle " + meeting.getLieu());
        holder.itemBinding.meetingDeleteBtn.setOnClickListener(v -> onDeleteItem.deleteItem(meeting));
        holder.itemBinding.meetingAvatar.setBackgroundColor(setColor(meeting.getLieu()));
    }

    public int setColor(String location) {
        int color = 0;
        switch (location) {
            case "Peach" :
                color = 0xFFFC03A1 ;
                break;
            case "Mario" :
                color = 0xFFFC0303 ;
                break;
            case "Wario" :
                color = 0xFFD69D00 ;
                break;
            case "Bowser" :
                color = 0xFF001DD6 ;
                break;
            case "Luigi" :
                color = 0xFF00D604 ;
                break;
        }
        return color;
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
