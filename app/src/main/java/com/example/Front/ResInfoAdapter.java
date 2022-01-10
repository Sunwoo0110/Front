package com.example.Front;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResInfoAdapter extends RecyclerView.Adapter <ResInfoAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton ResImgView;
        public TextView ResNameView;
        public TextView ResPlaceView;
        public TextView ResTimeView;
        public RatingBar ResStarView;
        public TextView ResScoreView;

        public ViewHolder(View view) {
            super(view);
            ResImgView = (ImageButton) view.findViewById(R.id.res_img);
            ResNameView = (TextView) view.findViewById(R.id.res_name);
            ResPlaceView = (TextView) view.findViewById(R.id.res_place);
            ResTimeView = (TextView) view.findViewById(R.id.res_time);
            ResStarView = (RatingBar) view.findViewById(R.id.res_star);
            ResScoreView = (TextView) view.findViewById(R.id.res_score);
        }
    }

    @NonNull
    private ArrayList<ResData> mDataset = new ArrayList<ResData>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_res_info, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        //holder.ResImgView.setImageResource(mDataset.get(position).img);
        //for test
        holder.ResImgView.setImageResource(R.drawable.cat2);
        holder.ResNameView.setText(mDataset.get(position).name);
        holder.ResPlaceView.setText(mDataset.get(position).place);
        holder.ResTimeView.setText(mDataset.get(position).time);
        holder.ResStarView.setRating(mDataset.get(position).score);
        holder.ResScoreView.setText(String.valueOf(mDataset.get(position).score));

        holder.ResImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // click 시 필요한 동작 정의

                // 다음 fragment 으로 가게이름 전송


                // 다음 fragment 으로 이동
                Navigation.findNavController(view)
                        .navigate(R.id.action_navigation_res_info_to_navigation_review);
            }
        });

    }

    public ResInfoAdapter(ArrayList<ResData> searchDataSet, Activity activity){
        mDataset = searchDataSet;
    }


    @Override
    public int getItemCount() {
        return (mDataset != null? mDataset.size() : 0);
    }
}
