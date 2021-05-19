package com.muzikmasti.hindisongs90.RetrofitUtils;

import android.os.Handler;
import android.util.Log;

import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.Model.PlayList;
import com.muzikmasti.hindisongs90.Model.SongsMaster;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                        Global.API_KEY.clear();
                        Global.API_KEY.put("Banner", "" + response.body().getAdds().getBanner());
                        Global.API_KEY.put("Interstitial", "" + response.body().getAdds().getInterstitial());
                        Global.API_KEY.put("Native", "" + response.body().getAdds().getNative());
                        Global.API_KEY.put("Rewarded", "" + response.body().getAdds().getRewarded());

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

}
