package com.muzikmasti.hindisongs90.Activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

import com.muzikmasti.hindisongs90.Fragments.MainFragment;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RoomDatabase.DbViewmModel;
import com.muzikmasti.hindisongs90.RoomDatabase.Favourite;
import com.muzikmasti.hindisongs90.RoomDatabase.Recent;

public class MainActivity extends AppCompatActivity {
    public static DbViewmModel viewModel;
    DisplayMetrics displayMetrics;
    private AdView mAdView;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Global.mKProgressHUD = KProgressHUD.create(MainActivity.this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setDimAmount(0.7f).setAnimationSpeed(2).setLabel("Loading Playlists").setCancellable(true);
        Global.mKProgressHUD.show();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        initAds();
        initIntrestAds();
        Global.changeFragmentMain(MainActivity.this, new MainFragment(), "MainFragment", false);
    }

    private void initAds() {
        mAdView = (AdView) findViewById(R.id.banner_adView);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);

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

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void initIntrestAds() {
        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();

            }

            @Override
            public void onAdClosed() {
                //  Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

}