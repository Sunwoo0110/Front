package com.example.Front.Res;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.Front.R;

public class ResInfoAdapter extends RecyclerView.Adapter <ResInfoAdapter.ViewHolder> {

    @NonNull
    private ArrayList<ResData> mDataset;

    public ResInfoAdapter(ArrayList<ResData> searchDataSet, Activity activity){
        mDataset = searchDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_res_info, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        /*
        public ImageView ResImgView;
        public TextView ResNameView;
        public TextView ResPlaceView;
        public TextView ResTimeView;
        public TextView ResLikeView;

         */
        holder.ResImgView.setImageResource(mDataset.get(position).img);
        holder.ResNameView.setText(mDataset.get(position).name);
        holder.ResPlaceView.setText(mDataset.get(position).place);
        holder.ResTimeView.setText(mDataset.get(position).time);
        holder.ResLikeView.setText(mDataset.get(position).like);
        /*
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // click 시 필요한 동작 정의
            }
        });
         */
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ResImgView;
        public TextView ResNameView;
        public TextView ResPlaceView;
        public TextView ResTimeView;
        public TextView ResLikeView;

        public ViewHolder(View view) {
            super(view);
            ResImgView = (ImageView) view.findViewById(R.id.res_img);
            ResNameView = (TextView) view.findViewById(R.id.res_name);
            ResPlaceView = (TextView) view.findViewById(R.id.res_place);
            ResTimeView = (TextView) view.findViewById(R.id.res_time);
            ResLikeView = (TextView) view.findViewById(R.id.res_like);
        }
    }

}
