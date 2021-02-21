package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTxtView = findViewById(R.id.txt_view);
        Button myBtn = findViewById(R.id.btn);

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