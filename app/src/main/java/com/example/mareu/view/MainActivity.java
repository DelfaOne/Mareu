package com.example.mareu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mareu.MeetingViewModel;
import com.example.mareu.ViewModelFactory;
import com.example.mareu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        View view = vb.getRoot();
        setContentView(view);

        TextView myTxtView = vb.txtView;
        Button myBtn = vb.btn;

        MeetingViewModel meetingViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(MeetingViewModel.class);

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingViewModel.onButtonPressed();
            }
        });

        meetingViewModel.getVIewStateLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String value) {
                myTxtView.setText(value);
            }
        });
    }
}