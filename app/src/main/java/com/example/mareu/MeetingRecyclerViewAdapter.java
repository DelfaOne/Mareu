package com.example.mareu;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ItemListMeetingBinding;
import com.example.mareu.repository.Meeting;
import com.example.mareu.viewmodel.MeetingViewState;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private List<MeetingViewState> meetingList;
    private OnMeetingListener onMeetingListener;

    public MeetingRecyclerViewAdapter(OnMeetingListener onMeetingListener) {
        this.onMeetingListener = onMeetingListener;
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
        holder.itemBinding.meetingDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMeetingListener.onBtnDeleteClick(meeting);
            }
        });
    }

    public interface OnMeetingListener {
        void onBtnDeleteClick(MeetingViewState meetingViewState);
    }

    @Override
    public int getItemCount() {
        if (meetingList != null) {
            return meetingList.size();
        } else {
            return 0;
        }
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
