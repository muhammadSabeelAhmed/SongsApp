package com.muzikmasti.hindisongs90.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.muzikmasti.hindisongs90.Ads.AdmobIntrestitialAds;
import com.muzikmasti.hindisongs90.Ads.FacebookIntrestitialAds;
import com.muzikmasti.hindisongs90.Fragments.FacebookBanner;
import com.muzikmasti.hindisongs90.Fragments.GoogleBanner;
import com.muzikmasti.hindisongs90.Fragments.MainFragment;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RoomDatabase.DbViewmModel;
import com.muzikmasti.hindisongs90.RoomDatabase.Favourite;
import com.muzikmasti.hindisongs90.RoomDatabase.Recent;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static DbViewmModel viewModel;
    DisplayMetrics displayMetrics;
    AdmobIntrestitialAds admobIntrestitialAds;
    FacebookIntrestitialAds facebookIntrestitialAds;
    PreferencesHandler preferencesHandler;
    Handler myhandler;
    Runnable myRUnnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Global.mKProgressHUD = KProgressHUD.create(MainActivity.this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setDimAmount(0.7f).setAnimationSpeed(2).setLabel("Loading Playlists").setCancellable(true);
        Global.mKProgressHUD.show();
        init();

    }

    private void init() {
        preferencesHandler = new PreferencesHandler(MainActivity.this);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //Subscribe to Topic
        FirebaseMessaging.getInstance().subscribeToTopic("muzikmastihindisongs90");

        Global.height = displayMetrics.heightPixels;
        Global.width = displayMetrics.widthPixels;
        Log.d("HxW", "" + Global.height + "--" + Global.width);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(MainActivity.this).get(DbViewmModel.class);
        }
        favouriteHandler();
        recentHandler();
        Global.changeFragmentMain(MainActivity.this, new MainFragment(), "MainFragment", false);
        myhandler = new Handler();
        myRUnnable = new Runnable() {
            @Override
            public void run() {
                if (Global.playList.size() > 0) {
                    initAds();
                } else {
                    myhandler.postDelayed(myRUnnable, 500);
                }
            }
        };
        myhandler.post(myRUnnable);
    }

    public void initAds() {
        admobIntrestitialAds = new AdmobIntrestitialAds(MainActivity.this);
        facebookIntrestitialAds = new FacebookIntrestitialAds(MainActivity.this);
        if (preferencesHandler.getAds().equals("addmob")) {
            admobIntrestitialAds.initIntrestAds();
            Global.changeFragmentSplash(MainActivity.this, new GoogleBanner(), "GoogleBanner", false);
        } else if (preferencesHandler.getAds().equals("facebook")) {
            Global.changeFragmentSplash(MainActivity.this, new FacebookBanner(), "FacebookBanner", false);
            facebookIntrestitialAds.initIntrestAds();
        }

    }

    public void favouriteHandler() {
        MainActivity.viewModel.getAllFav().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                Global.favList = favourites;
            }
        });
    }

    public void recentHandler() {
        MainActivity.viewModel.getAllRecents().observe(this, new Observer<List<Recent>>() {
            @Override
            public void onChanged(List<Recent> recents) {
                Global.recentList = recents;
            }
        });
    }

}