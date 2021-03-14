package com.muzikmasti.hindisongs90.Ads;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.muzikmasti.hindisongs90.R;

import java.util.List;

public class nativeAds_google {

    private static UnifiedNativeAd nativeAd;

    public static void load(final Context context, final LinearLayout relativeLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, context.getResources().getString(R.string.admob_nativeads));

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = unifiedNativeAd;
                UnifiedNativeAdView adView = (UnifiedNativeAdView) ((Activity) context).
                        getLayoutInflater()
                        .inflate(R.layout.unifed_native_ad, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                relativeLayout.removeAllViews();
                relativeLayout.addView(adView);
            }
        });
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Toast.makeText(context, "error" + i, Toast.LENGTH_LONG).show();

            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    private static void populateUnifiedNativeAdView(UnifiedNativeAd unifiedNativeAd, UnifiedNativeAdView unifiedNativeAdView) {
        MediaView mediaView = (MediaView) unifiedNativeAdView.findViewById(R.id.ad_media);
        ImageView imageView = (ImageView) unifiedNativeAdView.findViewById(R.id.ad_image);
        TextView textView = (TextView) unifiedNativeAdView.findViewById(R.id.tv_ad);
        VideoController videoController = unifiedNativeAd.getVideoController();
        videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            public void onVideoEnd() {
                super.onVideoEnd();
            }
        });
        if (videoController.hasVideoContent()) {
            unifiedNativeAdView.setMediaView(mediaView);
            imageView.setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.setImageView(imageView);
            mediaView.setVisibility(View.GONE);
            List images = unifiedNativeAd.getImages();
            if (images.size() != 0) {
                imageView.setImageDrawable(((NativeAd.Image) images.get(0)).getDrawable());
            }
        }
        unifiedNativeAdView.setHeadlineView(unifiedNativeAdView.findViewById(R.id.ad_headline));
        unifiedNativeAdView.setBodyView(unifiedNativeAdView.findViewById(R.id.ad_body));
        unifiedNativeAdView.setCallToActionView(unifiedNativeAdView.findViewById(R.id.ad_call_to_action));
        unifiedNativeAdView.setIconView(unifiedNativeAdView.findViewById(R.id.ad_app_icon));
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
        ((Button) unifiedNativeAdView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
        if (unifiedNativeAd.getIcon() == null) {
            unifiedNativeAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) unifiedNativeAdView.getIconView()).setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
            unifiedNativeAdView.getIconView().setVisibility(View.VISIBLE);
        }
        unifiedNativeAdView.setNativeAd(unifiedNativeAd);
    }

}

