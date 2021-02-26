package com.example.mareu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ItemListMeetingBinding;
import com.example.mareu.viewmodel.MeetingViewState;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private List<MeetingViewState> meetingList;

    public MeetingRecyclerViewAdapter(List<MeetingViewState> meetingList) {
        this.meetingList = meetingList;
    }

    public void submitList(List<MeetingViewState> items) {
        this.meetingList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_meeting, parent, false);
        return new MeetingViewHolder(ItemListMeetingBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        MeetingViewState meeting = meetingList.get(position);
        holder.itemBinding.meetingTitle.setText(meeting.getReunionSubject() + " - " + meeting.getDate() + " - " + meeting.getLieu());
        holder.itemBinding.meetingParticipants.setText(meeting.getParticipants());
    }

    @Override
    public int getItemCount() {
        if (meetingList != null) {
            return meetingList.size();
        } else {
            return 0;
        }
    }

    static class MeetingViewHolder extends RecyclerView.ViewHolder {

        private ItemListMeetingBinding itemBinding;

        public MeetingViewHolder(ItemListMeetingBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
