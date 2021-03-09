package com.muzikmasti.hindisongs90.Admob;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;

import com.muzikmasti.hindisongs90.R;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the AdMob app
     FirebaseApp.initializeApp(this);
      MobileAds.initialize(this, getString(R.string.admob_app_id));
    }
}