package com.muzikmasti.hindisongs90.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.muzikmasti.hindisongs90.Ads.ActivityConfig;
import com.muzikmasti.hindisongs90.R;

public class FacebookBanner extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_facebook_banner, container, false);
        AdSettings.addTestDevice("328404cebf50ec1fdb05795c0007a8a7");
        loadAdFifty();
        return v;
    }

    private void loadAdFifty() {
        RelativeLayout adContainer = v.findViewById(R.id.ad_banner_50);
        AdView adView = new AdView(v.getContext(), ActivityConfig.FB_BANNER_50, AdSize.BANNER_HEIGHT_50);
        adContainer.addView(adView);
        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(v.getContext(), "Ad 50 Error: " + adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Toast.makeText(v.getContext(), "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig()
                .withAdListener(adListener)
                .build();
        adView.loadAd(loadAdConfig);
    }

}