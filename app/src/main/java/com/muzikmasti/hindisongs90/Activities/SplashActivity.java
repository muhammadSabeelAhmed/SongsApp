package com.muzikmasti.hindisongs90.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.muzikmasti.hindisongs90.Fragments.FacebookBanner;
import com.muzikmasti.hindisongs90.Fragments.GoogleBanner;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RetrofitUtils.PostWebAPIData;

public class SplashActivity extends AppCompatActivity {
    PostWebAPIData postWebAPIData;
    LinearLayout splash_layout, main;
    PreferencesHandler preferencesHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        startActivity();
    }

    private void init() {
        preferencesHandler = new PreferencesHandler(SplashActivity.this);
        preferencesHandler.setCurrentPlaylist("");
        preferencesHandler.setAds("");
        if (preferencesHandler.getAds().equals("facebook")) {
            Global.changeFragmentSplash(SplashActivity.this, new FacebookBanner(), "FacebookBanner", false);
        } else if (preferencesHandler.getAds().equals("addmob")) {
            Global.changeFragmentSplash(SplashActivity.this, new GoogleBanner(), "GoogleBanner", false);
        }
//
//        if (preferencesHandler.getAds().equals("facebook")) {
//            mAdView.setVisibility(View.GONE);
//            loadFbAds();
//        } else {
//            adContainer.setVisibility(View.GONE);
//            mAdView.setVisibility(View.VISIBLE);
//        }

        postWebAPIData = new PostWebAPIData();
        postWebAPIData.GetAppData("164", "test");
        splash_layout = findViewById(R.id.layout);
        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        splash_layout.startAnimation(animZoomIn);
    }


    private void startActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Global.changeActivity(SplashActivity.this, new MainActivity());
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 5000);
    }

}