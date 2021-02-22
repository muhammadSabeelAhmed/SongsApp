package app.sabeeldev.mysongs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RetrofitUtils.PostWebAPIData;

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