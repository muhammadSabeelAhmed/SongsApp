package app.sabeeldev.mysongs.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ui.PlayerView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.sabeeldev.mysongs.PlayerSettings.ExoPlayerManager;
import app.sabeeldev.mysongs.R;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class Player extends AppCompatActivity {

    private String YOUTUBE_VIDEO_ID = "U_DSCLqgZCo";
    private String BASE_URL = "http://www.youtube.com";
    private String mYoutubeLink = BASE_URL + "/watch?v=" + YOUTUBE_VIDEO_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        extractYoutubeUrl();

    }

    private void extractYoutubeUrl() {
        @SuppressLint("StaticFieldLeak") YouTubeExtractor mExtractor = new YouTubeExtractor(this) {
            @Override
            protected void onExtractionComplete(SparseArray<YtFile> sparseArray, VideoMeta videoMeta) {
                if (sparseArray != null) {
                    Log.d("MyURL", "" + sparseArray.size());
//                    for (int i = 0; i < sparseArray.size(); i++) {
//                        if (sparseArray.get(i).getUrl() != null) {
//                            Log.d("MyURL", "" + sparseArray.get(i).getUrl());
//                        } else {
//                            Log.d("MyURL", "Null;");
//
//                        }
//
//                    }
                    playVideo(sparseArray.get(16).getUrl());
                }
            }
        };
        mExtractor.extract(mYoutubeLink, true, true);
    }

    private void playVideo(String downloadUrl) {
        PlayerView mPlayerView = findViewById(R.id.mPlayerView);
        mPlayerView.setPlayer(ExoPlayerManager.getSharedInstance(Player.this).getPlayerView().getPlayer());
        ExoPlayerManager.getSharedInstance(Player.this).playStream(downloadUrl);
    }

}