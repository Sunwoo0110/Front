package com.example.Front;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ResInfoFragment extends Fragment {

    private RecyclerView reRecyclerView;
    private ResInfoAdapter reAdapter;
    private RecyclerView.LayoutManager reLayoutManager;
    private ArrayList<ResData> reResData = new ArrayList<ResData>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Log.d("TEST", getJsonString());
        jsonParsing(getJsonString());

        // test
        /*
        ResData resData = new ResData();

        resData.setName("카이마루");
        resData.setPlace("카이스트");
        resData.setTime("몰랑");
        resData.setLike(2);
        reResData.add(resData);

         */


        View view = inflater.inflate(R.layout.fragment_res_info_list, container, false);
        reRecyclerView = (RecyclerView) view.findViewById(R.id.res_list);
        reRecyclerView.setHasFixedSize(true);
        reLayoutManager = new LinearLayoutManager(getActivity());
        reRecyclerView.setLayoutManager(reLayoutManager);
        reRecyclerView.scrollToPosition(0);
        reAdapter = new ResInfoAdapter(reResData, getActivity());
        reRecyclerView.setAdapter(reAdapter);
        reRecyclerView.setItemAnimator(new DefaultItemAnimator());
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

    // resList.json 읽어오기
    private String getJsonString()
    {
        String json = "";
        try {
            InputStream is = getActivity().getResources().getAssets().open("resList.json");
            Log.d("test!!!!!!", "test1");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    // json 파싱
    private void jsonParsing(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray resArray = jsonObject.getJSONArray("resList");

            for(int i=0; i<resArray.length(); ++i){
                JSONObject resObject = resArray.getJSONObject(i);

                ResData resData = new ResData();

                resData.setName(resObject.getString("name"));
                Log.d("name", resData.getName());
                resData.setPlace(resObject.getString("place"));
                Log.d("place", resData.getPlace());
                resData.setTime(resObject.getString("time"));
                Log.d("time", resData.getTime());
                resData.setLike(resObject.getInt("like"));

                reResData.add(resData);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}