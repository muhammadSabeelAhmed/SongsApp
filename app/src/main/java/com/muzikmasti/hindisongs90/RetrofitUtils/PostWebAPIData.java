package com.muzikmasti.hindisongs90.RetrofitUtils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.muzikmasti.hindisongs90.Activities.Player;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.Model.PlayList;
import com.muzikmasti.hindisongs90.Model.SongsMaster;
import com.muzikmasti.hindisongs90.Model.Video;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.muzikmasti.hindisongs90.GeneralClasses.Global.API_KEY;
import static com.muzikmasti.hindisongs90.GeneralClasses.Global.CURRENT_API;
import static com.muzikmasti.hindisongs90.GeneralClasses.Global.mySongslists;
import static com.muzikmasti.hindisongs90.GeneralClasses.Global.playList;

public class PostWebAPIData {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    APIInterface videoApiInterface = VideoClient.getClient().create(APIInterface.class);
    PreferencesHandler preferencesHandler;

    public void GetAppData(String pid, String password) {
        preferencesHandler = new PreferencesHandler();
        if (NetworkConnectivity.isOnline()) {
            Call<PlayList> call = apiInterface.getPlayListData(pid, password);
            call.enqueue(new Callback<PlayList>() {
                @Override
                public void onResponse(Call<PlayList> call, Response<PlayList> response) {
                    if (response.isSuccessful()) {
                        Global.playList.clear();
                        Global.mySongslists.clear();
                        mySongslists = response.body().getSongss();
                        preferencesHandler.setAds(response.body().getAdds().getAddType().toLowerCase());
                        //   preferencesHandler.setAds("facebook");
                        for (int i = 0; i < mySongslists.size(); i++) {
                            if (!Global.playList.contains(mySongslists.get(i).getAlbumName())) {
                                Global.playList.add(mySongslists.get(i).getAlbumName());
                            }
                        }

                        for (int i = 0; i < playList.size(); i++) {
                            String currentList = playList.get(i);
                            ArrayList<PlayList.Songs> currentSongs = new ArrayList<>();
                            for (int j = 0; j < Global.mySongslists.size(); j++) {
                                if (Global.mySongslists.get(j).getAlbumName().equals(currentList)) {
                                    currentSongs.add(mySongslists.get(j));
                                }
                            }
                            Log.d("CurrentSongsSize", "" + currentSongs.size());
                            Global.sortedList.add(new SongsMaster(currentList, currentSongs));
                        }
                    }
                }

                @Override
                public void onFailure(Call<PlayList> call, Throwable t) {
                    Log.d("MyResponse", "failure" + t.getMessage());
                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    GetAppData(pid, password);
                }
            }, 4000);
        }
    }


    public void GetVideoData(String videoId, Context context) {
        if (NetworkConnectivity.isOnline()) {
            Global.mKProgressHUD = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setDimAmount(0.7f).setAnimationSpeed(2).setLabel("Loading Song\nPlease wait").setCancellable(true);
            Global.mKProgressHUD.show();
            Call<Video> call = videoApiInterface.getVideoDetails(videoId, CURRENT_API, "youtube-video-info.p.rapidapi.com", "true");
            call.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Call<Video> call, Response<Video> response) {
                    if (response.isSuccessful()) {
                        Video videoExtrat = response.body();
                        if (videoExtrat.getStatus() == null) {
                            Global.videosFormats.clear();
                            Global.videosFormats = videoExtrat.getAllFormats();
                            //Global.videoTitle = videoExtrat.getVideoTitle();
                            Global.duration = videoExtrat.getDuration();
                            // Player.song_title.setText(Global.videoTitle);
                            // Player.song_duration.setText("Duration: " + Global.duration);
                            Global.changeActivity(context, new Player());
                        } else if (videoExtrat.getmessage() != null) {
                            Global.message = videoExtrat.getmessage();
                            if (videoExtrat.getmessage().contains("You have exceeded the DAILY quota for Requests on your current plan")) {
                                if (CURRENT_API.equals(API_KEY[0])) {
                                    CURRENT_API = API_KEY[1];
                                } else if (CURRENT_API.equals(API_KEY[1])) {
                                    CURRENT_API = API_KEY[0];
                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        GetVideoData(videoId, context);
                                    }
                                }, 4000);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Video> call, Throwable t) {
                    Log.d("MyResponse", "failure" + t.getMessage());
                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    GetVideoData(videoId, context);
                }
            }, 4000);
        }
    }

}
