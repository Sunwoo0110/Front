package com.example.Front;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter <ReviewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RatingBar ReviewRateView;
        public TextView ReviewScoreView;
        public TextView ReviewMemoView;

        public ViewHolder(View view) {
            super(view);

            ReviewRateView = (RatingBar) view.findViewById(R.id.review_star);
            ReviewScoreView = (TextView) view.findViewById(R.id.review_score);
            ReviewMemoView = (TextView) view.findViewById(R.id.review_memo);

        }
    }

    @NonNull
    private ArrayList<ReviewData> revDataset = new ArrayList<ReviewData>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.ReviewRateView.setRating(revDataset.get(position).rate);
        holder.ReviewScoreView.setText(String.valueOf(revDataset.get(position).rate));
        holder.ReviewMemoView.setText(revDataset.get(position).review);
        /*
        holder.ResNameView.setOnClickListener(new View.OnClickListener() {
            private Object ResInfoFragment;

            @Override
            public void onClick(View view) {
                // click 시 필요한 동작 정의
                NavHostFragment.findNavController((Fragment) ResInfoFragment)
                        .navigate(R.id.action_navigation_res_info_to_navigation_review);
            }
        });

         */

    }

    public ReviewAdapter(ArrayList<ReviewData> searchDataSet, Activity activity){
        revDataset = searchDataSet;
    }


    @Override
    public int getItemCount() {
        return (revDataset != null? revDataset.size() : 0);
    }
}
