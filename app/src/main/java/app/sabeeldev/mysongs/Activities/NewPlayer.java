package app.sabeeldev.mysongs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.YTParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.sabeeldev.mysongs.Adpater.PlayerAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.R;

import static app.sabeeldev.mysongs.GeneralClasses.Global.playListSelected;

public class NewPlayer extends AppCompatActivity implements View.OnClickListener {
    public static YoutubePlayerView youtubePlayerView;
    Button btn_audio, btn_video;
    public static TextView song_title, song_album, song_duration;
    RecyclerView recyclerView;
    PlayerAdapter playListAdapter;
    TextView title;
    // url of video which we are loading.
    public static ImageView videoImg;
    ArrayList<PlayList.Songs> myPlayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();


        // psuse video
        //youtubePlayerView.pause();
        // play video when it's ready


    }

    public static void loadVideo(String videoId) {
        // initialize YoutubePlayerCallBackListener and VideoID
        youtubePlayerView.initialize(videoId, new YoutubePlayerView.YouTubeListener() {

            @Override
            public void onReady() {
                // when player is ready.
            }

            @Override
            public void onStateChange(YoutubePlayerView.STATE state) {
                /**
                 * YoutubePlayerView.STATE
                 *
                 * UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, CUED, NONE
                 *
                 */
            }

            @Override
            public void onPlaybackQualityChange(String arg) {
            }

            @Override
            public void onPlaybackRateChange(String arg) {
            }

            @Override
            public void onError(String error) {
            }

            @Override
            public void onApiChange(String arg) {
            }

            @Override
            public void onCurrentSecond(double second) {
                // currentTime callback
            }

            @Override
            public void onDuration(double duration) {
                song_duration.setText("Duration: " + convertTime(duration));
            }

            @Override
            public void logs(String log) {
                // javascript debug log. you don't need to use it.
            }
        });
        youtubePlayerView.play();

    }

    private void init() {
        youtubePlayerView = (YoutubePlayerView) findViewById(R.id.youtubePlayerView);
        YTParams params = new YTParams();
// make auto height of youtube. if you want to use 'wrap_content'
        youtubePlayerView.setAutoPlayerHeight(this);

        btn_audio = findViewById(R.id.btn_audio);
        btn_audio.setOnClickListener(this);

        btn_video = findViewById(R.id.btn_video);
        btn_video.setOnClickListener(this);

        song_title = findViewById(R.id.playedSong_title);
        song_title.setText(Global.videoTitle);
        playListAdapter = new PlayerAdapter();
        song_album = findViewById(R.id.playedSong_album);
        song_duration = findViewById(R.id.playedSong_duration);
        title = findViewById(R.id.player_title);

        title.setText(playListSelected);
        song_album.setText(playListSelected);

        videoImg = findViewById(R.id.videoimage);
        recyclerView = findViewById(R.id.player_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewPlayer.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(playListAdapter);
        Log.d("MyfavSize", "" + Global.favList.size());

        if (Global.playerChecker.equals("fav")) {
            title.setText("Favorites Playlist");
            myPlayList.clear();
            for (int i = 0; i < Global.favList.size(); i++) {
                myPlayList.add(new PlayList.Songs("" + Global.favList.get(i).getAlbumID(), "" + Global.favList.get(i).getAlbumName(), "" + Global.favList.get(i).getAlbumsort(),
                        "" + Global.favList.get(i).getSongID(), "" + Global.favList.get(i).getTitle(), "" + Global.favList.get(i).getWebview(), "" + Global.favList.get(i).getIsRedirection(),
                        "" + Global.favList.get(i).getRedirectionApp(), "" + Global.favList.get(i).getImage(), "" + Global.favList.get(i).getYoutubecode(), "" + Global.favList.get(i).getSongSortorder()));
            }
            playListAdapter.addAllItems(myPlayList);
        } else if (Global.playerChecker.equals("recent")) {
            title.setText("Recent Playlist");
            myPlayList.clear();
            for (int i = 0; i < Global.recentList.size(); i++) {
                myPlayList.add(new PlayList.Songs("" + Global.recentList.get(i).getAlbumID(), "" + Global.recentList.get(i).getAlbumName(), "" + Global.recentList.get(i).getAlbumsort(),
                        "" + Global.recentList.get(i).getSongID(), "" + Global.recentList.get(i).getTitle(), "" + Global.recentList.get(i).getWebview(), "" + Global.recentList.get(i).getIsRedirection(),
                        "" + Global.recentList.get(i).getRedirectionApp(), "" + Global.recentList.get(i).getImage(), "" + Global.recentList.get(i).getYoutubecode(), "" + Global.recentList.get(i).getSongSortorder()));
            }
            playListAdapter.addAllItems(myPlayList);
        } else {
            for (int i = 0; i < Global.playList.size(); i++) {
                if (Global.playList.get(i).equals(playListSelected)) {
                    playListAdapter.addAllItems(Global.sortedList.get(i).getMysongs());
                }
            }
        }
        //  myHandler.post(myRunnable);
        Picasso.get().load(Global.imgURL).into(videoImg);
        loadVideo(Global.videoCode);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // pause video when on the background mode.
        youtubePlayerView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // this is optional but you need.
        youtubePlayerView.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_audio:
                break;
            case R.id.btn_video:
                break;
        }
    }

    public static String convertTime(double sec) {
        int seconds = (int) sec;
        int p1 = seconds % 60;
        int p2 = seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        return String.format("%02d", p2) + ":" + String.format("%02d", p3) + ":" + String.format("%02d", p1);
    }
}