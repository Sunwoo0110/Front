package com.example.Front;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.app.Fragment;

import androidx.annotation.Nullable;
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
    String res_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //server 에서 받아서 revResData add 하기

        View view = inflater.inflate(R.layout.fragment_review, container, false);

        Bundle getbundle = getArguments();
        if(getbundle != null){
            String S_json = getbundle.getString("S_json");
            Log.d("PrintBundle", S_json);
            // 받은 리뷰 정보 Parsing
            jsonParsing(S_json);
        }

        // id/res_title 변수 변경
        TextView textview = (TextView) view.findViewById(R.id.res_title);
        textview.setText(res_name);

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

    // json 파싱
    private void jsonParsing(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.d("stesdga", json);
            res_name = jsonObject.getString("res_name");

            JSONArray resArray = jsonObject.getJSONArray("resList");

            for(int i=0; i<resArray.length(); ++i){
                JSONObject resObject = resArray.getJSONObject(i);

                ReviewData revData = new ReviewData();

                revData.setName(resObject.getString("name"));
                Log.d("name", revData.getName());
                revData.setRate((float) resObject.getDouble("rate"));
                Log.d("rate", revData.getRate().toString());
                revData.setReview(resObject.getString("review"));
                Log.d("review", revData.getReview());

                revResData.add(revData);
            }

        } catch (JSONException e){
            e.printStackTrace();
            Log.d("ssdf", json);
        }
    }

}