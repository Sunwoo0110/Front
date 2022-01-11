package com.example.Front;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.RatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentFragment extends Fragment {

    private final String URL = "http://172.10.18.160:80";

    private static final String TAG = "CommentFragment";
    private Activity mActivity;

    private Retrofit retrofit;
    private ApiService3 service;


    public void firstInit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService3.class);
    }
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

                Bundle getbundle = getArguments();
                String resName = "";
                if(getbundle != null){
                    resName = getbundle.getString("resName");
                    Log.d("PrintBundle", resName);
                }
                String id = "";
                try {
                    id = MainActivity.userInfo.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                firstInit();
                // memo input 받기
                String input = inputView.getText().toString();
                // star input 받기
                Float star = score.getRating();

                JSONObject commentInfo = new JSONObject();

                try {
                    commentInfo.accumulate("id", id);
                    commentInfo.accumulate("resName", resName);
                    commentInfo.accumulate("rate", star.toString());
                    commentInfo.accumulate("memo", input);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // + server 로 보내야함
                // json 에 사용자 id, 가게 이름, rating, memo packing 해서 보내기
                Call<ResponseBody> call_post = service.postFunc(commentInfo.toString());
                call_post.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                // server 에서 값 받기
                                String result = response.body().string();
                                Log.d(TAG, "result = " + result);

                                // json ReviewFragment 로 보내기
                                Bundle bundle = new Bundle();
                                bundle.putString("S_json", result);

                                ReviewFragment reviewFragment = new ReviewFragment();

                                reviewFragment.setArguments(bundle);
                                Log.d("rwasdfsdagr", bundle.toString());

                                mActivity.getFragmentManager()
                                        .beginTransaction()
                                        .add(reviewFragment, "ss")
                                        .replace(R.id.resContainer, reviewFragment)
                                        .commit();

                                //Navigation.findNavController(view)
                                //.navigate(R.id.action_navigation_res_info_to_navigation_review);

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

                // 리뷰 리스트로 돌아가기
                    /*
                Navigation.findNavController(view)
                        .navigate(R.id.action_navigation_comment_to_navigation_review);

                     */
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