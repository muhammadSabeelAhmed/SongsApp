package com.muzikmasti.hindisongs90.Ads;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;

public class AdmobIntrestitialAds {
    Context context;
    InterstitialAd mInterstitialAd;

    public AdmobIntrestitialAds(Context context) {
        this.context = context;
    }

    public void initIntrestAds() {
        mInterstitialAd = new InterstitialAd(context);
        // set the ad unit ID
      mInterstitialAd.setAdUnitId(Global.API_KEY.get("Interstitial"));
        //mInterstitialAd.setAdUnitId("ca-app-pub-6441488087214922/2168335242");

//        // set the ad unit ID
//        AdRequest adRequest = new AdRequest.Builder()
//                .setRequestAgent("android_studio:ad_template").build();

        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
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
              //  Toast.makeText(context, "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                //Toast.makeText(context, "Ad left application!", Toast.LENGTH_SHORT).show();
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
