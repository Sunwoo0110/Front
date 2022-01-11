package com.example.Front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.Front.R;

public class ResActivity extends AppCompatActivity {

    ResInfoFragment resInfoFragment;
    ReviewFragment reviewFragment;
    CommentFragment commentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        Intent intent = getIntent();

        resInfoFragment = new ResInfoFragment();
        reviewFragment = new ReviewFragment();
        commentFragment = new CommentFragment();

    }
}