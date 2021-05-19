package com.muzikmasti.hindisongs90.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.R;

public class GoogleBanner extends Fragment {


    private AdView mAdView;
    RelativeLayout adLayout;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_google_banner, container, false);
        init();
        return v;
    }

    private void init() {
        adLayout = v.findViewById(R.id.banner_adView);
        mAdView = new AdView(v.getContext());
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(Global.API_KEY.get("Banner"));
       // mAdView.setAdUnitId("ca-app-pub-6441488087214922/5061064224");
        adLayout.addView(mAdView);
        initAds();
    }

    private void initAds() {
      //  AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();

        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                //  Toast.makeText(v.getContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //Toast.makeText(v.getContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                //Toast.makeText(v.getContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
    }

}