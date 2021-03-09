package com.muzikmasti.hindisongs90.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.Locale;

import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RetrofitUtils.PostWebAPIData;

public class SplashActivity extends AppCompatActivity {
    PostWebAPIData postWebAPIData;
    LinearLayout splash_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        startActivity();

    }

    private void init() {
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
        }, 4000);
    }
}