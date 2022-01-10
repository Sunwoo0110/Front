package com.example.Front;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.gson.Gson;

public class CommentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        Button addReview = (Button) view.findViewById(R.id.comment_add);
        EditText inputView = (EditText) view.findViewById(R.id.reviewInput);
        RatingBar score = (RatingBar) view.findViewById(R.id.ratingBar);
        addReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // memo input 받기
                String input = inputView.getText().toString();
                // star input 받기
                Float star = score.getRating();

                

                // 리뷰 리스트로 돌아가기
                Navigation.findNavController(view)
                        .navigate(R.id.action_navigation_comment_to_navigation_review);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }}