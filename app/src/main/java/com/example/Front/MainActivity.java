package com.example.Front;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private ImageButton login;
    private Button logout;
    private CallbackFunction sessionCallback = new CallbackFunction();
    Session session;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        logout = findViewById(R.id.logout);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        login.setOnClickListener(v -> {
            // 자동 로그인
            if (Session.getCurrentSession().checkAndImplicitOpen()) {
                Log.d(TAG, "onClick: 로그인 세션살아있음");
                // 카카오 로그인 시도 (창이 안뜬다.)
                sessionCallback.requestMe();
            } else {
                Log.d(TAG, "onClick: 로그인 세션끝남");
                // 카카오 로그인 시도 (창이 뜬다.)
                session.open(AuthType.KAKAO_LOGIN_ALL, MainActivity.this);
            }
            /*
            Intent intent = new Intent(getApplicationContext(), ResActivity.class);
            startActivity(intent);
            finish();

             */

       });

        logout.setOnClickListener(v -> {
            Log.d(TAG, "onCreate:click ");
            UserManagement.getInstance()
                    .requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            super.onSessionClosed(errorResult);
                            Log.d(TAG, "onSessionClosed: " + errorResult.getErrorMessage());

                        }

                        @Override
                        public void onCompleteLogout() {
                            if (sessionCallback != null) {
                                Session.getCurrentSession().removeCallback(sessionCallback);
                            }
                            Log.d(TAG, "onCompleteLogout:logout ");
                        }
                    });
        });

        // 카카오 개발자 홈페이지에 등록할 해시키 구하기
       //getHashKey();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void getHashKey() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    public class CallbackFunction implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        // 로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
        }

        // 사용자 정보 요청
        public void requestMe() {
            // 사용자 정보 JSON 형식으로 서버로 전달
            JSONObject userInfo = new JSONObject();
            UserManagement.getInstance()
                    .me(new MeV2ResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                        }

                        @Override
                        public void onFailure(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                        }

                        @Override
                        public void onSuccess(MeV2Response result) {
                            try {
                                String id = String.valueOf(result.getId());
                                UserAccount kakaoAccount = result.getKakaoAccount();
                                //Intent intent = new Intent(getApplicationContext(), ResActivity.class);
                                Log.i("KAKAO_API", "사용자 아이디: " + result.getId());
                                userInfo.put("ID", result.getId());

                                if (kakaoAccount != null) {

                                    // 이메일
                                    String email = kakaoAccount.getEmail();
                                    Profile profile = kakaoAccount.getProfile();
                                    if (profile == null) {
                                        Log.d("KAKAO_API", "onSuccess:profile null ");
                                    } else {
                                        Log.d("KAKAO_API", "onSuccess:getProfileImageUrl " + profile.getProfileImageUrl());
                                        //intent.putExtra("profileImg", profile.getProfileImageUrl());
                                        // userInfo.put("profileImg", profile.getProfileImageUrl());
                                        Log.d("KAKAO_API", "onSuccess:getNickname " + profile.getNickname());
                                        //intent.putExtra("name", profile.getNickname());
                                        userInfo.put("name", profile.getNickname());
                                    }
                                    if (email != null) {

                                        Log.d("KAKAO_API", "onSuccess:email " + email);
                                        //intent.putExtra("email", kakaoAccount.getEmail());
                                        userInfo.put("email", email);
                                    } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                        // 동의 요청 후 이메일 획득 가능
                                        // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.
                                        Log.d("KAKAO_API", "onSuccess: 동의 요청 후 이메일 획득 가능");
                                    } else {
                                        // 이메일 획득 불가
                                        Log.d("KAKAO_API", "onSuccess: cannot get email");
                                    }

                                    // 프로필
                                    Profile _profile = kakaoAccount.getProfile();

                                    if (_profile != null) {

                                        Log.d("KAKAO_API", "nickname: " + _profile.getNickname());
                                        //intent.putExtra("name", _profile.getNickname());
                                        Log.d("KAKAO_API", "profile image: " + _profile.getProfileImageUrl());
                                        //intent.putExtra("profileImg", _profile.getProfileImageUrl());
                                        Log.d("KAKAO_API", "thumbnail image: " + _profile.getThumbnailImageUrl());
                                        //intent.putExtra("Thumnail", _profile.getThumbnailImageUrl());

                                    } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                        // 동의 요청 후 프로필 정보 획득 가능

                                    } else {
                                        // 프로필 획득 불가
                                    }
                                } else {
                                    Log.i("KAKAO_API", "onSuccess: kakaoAccount null");
                                }

                                // for json test
                                Log.d("Test: ID", String.valueOf(userInfo.get("ID")));
                                Log.d("Test: name", String.valueOf(userInfo.get("name")));
                                Log.d("Test: email", String.valueOf(userInfo.get("email")));
                                //startActivity(intent);
                                //finish();
                            } catch (JSONException ex){

                            }
                        }
                    });

            // userInfo node js 로 보내기
            
        }
    }
}
