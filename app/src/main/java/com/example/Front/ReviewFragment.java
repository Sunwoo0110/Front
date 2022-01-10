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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ReviewFragment extends Fragment {

    private RecyclerView reRecyclerView;
    private ReviewAdapter revAdapter;
    private RecyclerView.LayoutManager reLayoutManager;
    private ArrayList<ReviewData> revResData = new ArrayList<ReviewData>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //server 에서 받아서 revResData add 하기

        View view = inflater.inflate(R.layout.fragment_review, container, false);

        // id/res_title 변수 변경

        Bundle bundle = getArguments();
        if(bundle != null){
            String S_json = bundle.getString("S_json");
        }

        //recyclerview 선언
        reRecyclerView = (RecyclerView) view.findViewById(R.id.review_list);
        reRecyclerView.setHasFixedSize(true);
        reLayoutManager = new LinearLayoutManager(getActivity());
        reRecyclerView.setLayoutManager(reLayoutManager);
        reRecyclerView.scrollToPosition(0);
        revAdapter = new ReviewAdapter(revResData, getActivity());
        reRecyclerView.setAdapter(revAdapter);
        reRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Button goCmtBtn = (Button) view.findViewById(R.id.review_add);
        goCmtBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Navigation.findNavController(view)
                        .navigate(R.id.action_navigation_review_to_navigation_comment);
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
    }

}