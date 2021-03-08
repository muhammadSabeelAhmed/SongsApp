package app.sabeeldev.mysongs.Admob;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

import app.sabeeldev.mysongs.R;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));
    }
}