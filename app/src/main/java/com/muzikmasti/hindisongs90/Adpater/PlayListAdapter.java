package com.muzikmasti.hindisongs90.Adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.button.MaterialButton;
import com.muzikmasti.hindisongs90.Activities.MainActivity;
import com.muzikmasti.hindisongs90.Activities.NewPlayer;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.Model.PlayList;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RetrofitUtils.PostWebAPIData;
import com.muzikmasti.hindisongs90.RoomDatabase.Recent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.muzikmasti.hindisongs90.GeneralClasses.Global.playerChecker;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList.Songs> newsongsPlayList;
    PostWebAPIData postWebAPIData;
    PreferencesHandler preferencesHandler;

    public PlayListAdapter() {
        preferencesHandler = new PreferencesHandler();
        postWebAPIData = new PostWebAPIData();
        newsongsPlayList = new ArrayList<>();
    }

    public void addInner(List<PlayList.Songs> inners) {
        newsongsPlayList.clear();
        newsongsPlayList.addAll(inners);
        //}
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.playlist_items, parent, false);
        return new PlayListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //  if (newsongsPlayList.get(position).getAlbumName().equals(currentPlayList)) {
        if (newsongsPlayList.get(position).getAlbumName().contains("Apps") || !newsongsPlayList.get(position).getAlbumsort().contains("ads") || (!preferencesHandler.getAds().equals("addmob") && !preferencesHandler.getAds().equals("facebook"))) {
            holder.song_title.setVisibility(View.VISIBLE);
            holder.song_img.setVisibility(View.VISIBLE);
            holder.admobLayout.setVisibility(View.GONE);
            holder.nativeAdLayout.setVisibility(View.GONE);
            holder.playlist_card.setVisibility(View.VISIBLE);
            Picasso.get().load(newsongsPlayList.get(position).getImage()).into(holder.song_img);
            holder.song_title.setText(newsongsPlayList.get(position).getTitle());
            FrameLayout.LayoutParams mycurrentparams = new FrameLayout.LayoutParams((Global.height / 5) - 120, (Global.height / 5) - 120);
            holder.song_img.setLayoutParams(mycurrentparams);
            holder.song_title.setVisibility(View.VISIBLE);
            if (newsongsPlayList.get(position).getAlbumName().contains("Apps")) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((Global.width / 2) - 60, (Global.height / 5) - 60);
                holder.song_img.setLayoutParams(layoutParams);
                holder.song_title.setVisibility(View.GONE);
                //  holder.playlist_card.setBackgroundResource(R.color.orange_light);
            }
            holder.playlist_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerChecker = "normal";
                    preferencesHandler.setCurrentPlaylist("");
                    if (newsongsPlayList.get(position).getAlbumName().contains("Apps")) {
                        loadURL("https://play.google.com/store/apps/details?id=com.muzikmasti.oldhindivideosongs");
                    } else if (!newsongsPlayList.get(position).getYoutubecode().equals("0") && !newsongsPlayList.get(position).getYoutubecode().equals("")) {
                        Recent recent = new Recent("" + newsongsPlayList.get(position).getAlbumID(), "" + newsongsPlayList.get(position).getAlbumName(), "" + newsongsPlayList.get(position).getAlbumsort(),
                                "" + newsongsPlayList.get(position).getSongID(), "" + newsongsPlayList.get(position).getTitle(), "" + newsongsPlayList.get(position).getWebview(),
                                "" + newsongsPlayList.get(position).getIsRedirection(), "" + newsongsPlayList.get(position).getRedirectionApp(), "" + newsongsPlayList.get(position).getImage(),
                                "" + newsongsPlayList.get(position).getYoutubecode(), "" + newsongsPlayList.get(position).getSongSortorder());
                        boolean check = false;
                        int index = 0;
                        for (int i = 0; i < Global.recentList.size(); i++) {
                            if (Global.recentList.get(i).getTitle().equals(recent.getTitle())) {
                                check = true;
                                index = i;
                                break;
                            }
                        }
                        if (!check) {
                            MainActivity.viewModel.insertRecent(recent);
                            Global.recentList.add(recent);
                        }

                        Global.playListSelected = newsongsPlayList.get(position).getAlbumName();
                        Global.imgURL = newsongsPlayList.get(position).getImage();
                        Global.videoTitle = newsongsPlayList.get(position).getTitle();
                        Global.videoCode = newsongsPlayList.get(position).getYoutubecode();

                        Global.duration = "";
                        //  postWebAPIData.GetVideoData(newsongsPlayList.get(position).getYoutubecode(),context);
                        Global.changeActivity(context, new NewPlayer());
                    } else {
                        Toast.makeText(context, "Invalid Video Code", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Log.d("AdView", "" + position);
            if (preferencesHandler.getAds().equals("addmob")) {
                holder.song_title.setText("Advertisement");
                holder.song_img.setVisibility(View.GONE);
                FrameLayout.LayoutParams mycurrentparams = new FrameLayout.LayoutParams((Global.height / 5) - 120, (Global.height / 5) - 120);
                holder.admobLayout.setVisibility(View.VISIBLE);
                holder.admobLayout.setLayoutParams(mycurrentparams);
                initNativeAdmob(holder.mAdView);
            } else {
                holder.song_title.setText("Advertisement");
                holder.song_img.setVisibility(View.GONE);
                FrameLayout.LayoutParams mycurrentparams = new FrameLayout.LayoutParams((Global.height / 5) - 120, (Global.height / 5) - 120);
                holder.nativeLayout.setLayoutParams(mycurrentparams);
                holder.nativeAdLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        Log.d("MySongsSize", "" + newsongsPlayList.size());
        if (newsongsPlayList.size() >= 7) {
            size = 7;
        } else if (newsongsPlayList.size() <= 7) {
            size = newsongsPlayList.size();
        }
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView song_img;
        TextView song_title;
        RelativeLayout playlist_card;
        AdView mAdView;
        NativeAd nativeAd;
        NativeAdLayout nativeAdLayout;
        LinearLayout nativeLayout;
        RelativeLayout admobLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAdView=new AdView(itemView.getContext());
            song_img = itemView.findViewById(R.id.playlistSong_img);
            song_title = itemView.findViewById(R.id.playlistSong_title);
            playlist_card = itemView.findViewById(R.id.playlist_card);
            admobLayout = (RelativeLayout) itemView.findViewById(R.id.banner_adView);
            nativeAdLayout = itemView.findViewById(R.id.native_ad_container);
            nativeLayout = itemView.findViewById(R.id.nativeAdLayout);
            nativeAdLayout.setVisibility(View.GONE);
            if (preferencesHandler.getAds().equals("facebook")) {
                loadNativeAd(itemView);
            }else{
                mAdView.setAdSize(AdSize.LARGE_BANNER);
                mAdView.setAdUnitId(Global.API_KEY.get("Banner"));
                admobLayout.addView(mAdView);
            }

        }


        private void loadNativeAd(View v) {
            nativeAd = new NativeAd(context, Global.API_KEY.get("Native"));
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    Toast.makeText(context, "Error: " + adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd == null || nativeAd != ad) {
                        return;
                    }
                    inflateAd(nativeAd, v);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            };
            nativeAd.loadAd(nativeAd.buildLoadAdConfig()
                    .withAdListener(nativeAdListener)
                    .build());

        }

        private void inflateAd(NativeAd nativeAd, View v) {
            nativeAd.unregisterView();
            nativeAdLayout = v.findViewById(R.id.native_ad_container);
            LayoutInflater inflater = LayoutInflater.from(context);
            View adView = inflater.inflate(R.layout.item_native_ad, nativeAdLayout, false);
            nativeAdLayout.addView(adView);

            LinearLayout adChoicesContainer = v.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            MaterialButton nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdTitle.setText(nativeAd.getAdvertiserName());
            nativeAdBody.setText(nativeAd.getAdBodyText());
            nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
            sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            nativeAd.registerViewForInteraction(
                    adView, nativeAdMedia, nativeAdIcon, clickableViews);
        }

    }

    public void loadURL(String url) {
        if (url != null && !url.equals("")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        }
    }

    private void initNativeAdmob(AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
             //   Toast.makeText(context, "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
          //      Toast.makeText(context, "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
           //     Toast.makeText(context, "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
        mAdView.loadAd(adRequest);
    }
}
