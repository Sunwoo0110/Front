package com.example.Front;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kakao.auth.Session;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResInfoAdapter extends RecyclerView.Adapter <ResInfoAdapter.ViewHolder> {

    Session session;
    private static final String TAG = "ResInfoAdatper";

    private final String URL = "http://172.10.18.160:80";

    private Retrofit retrofit;
    private ApiService2 service;

    public void firstInit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService2.class);
    }

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
    //int position
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

                // server 로 클릭된 가게 이름 전송
                firstInit();
                //mDataset.get(position).name
                Call<ResponseBody> call_post = service.postFunc((String) holder.ResNameView.getText());
                call_post.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.d(TAG, "result = " + result);
                                //get_text.setText(result);
                                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d(TAG, "error = " + String.valueOf(response.code()));
                            //Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "Fail");
                        //Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });

                // 가게 이름 보여주기
                //TextView title = view.findViewById(R.id.res_title);
                //title.setText(holder.ResNameView.getText());

                // ReviewFragment 으로 가게이름 전송
                /*
                Bundle bundle = new Bundle();
                bundle.putString("resName", mDataset.get(holder.getAdapterPosition()).name);

                ReviewFragment reviewFragment = new ReviewFragment();

                FragmentTransaction transaction = reviewFragment.getActivity().getSupportFragmentManager().beginTransaction();

                reviewFragment.setArguments(bundle);
                transaction.replace(R.id.nav_host_fragment_activity_res, reviewFragment);
                transaction.commit();

                 */


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
