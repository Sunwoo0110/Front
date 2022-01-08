package com.example.Front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.Front.R;

public class ResActivity extends AppCompatActivity {

    String name, email, profileImg,Thumnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        Intent intent = getIntent();

    }
}