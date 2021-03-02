package app.sabeeldev.mysongs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
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
import com.jaedongchicken.ytplayer.model.PlaybackQuality;
import com.jaedongchicken.ytplayer.model.YTParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.sabeeldev.mysongs.Adpater.PlayerAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.GeneralClasses.PreferencesHandler;
import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.R;

import static app.sabeeldev.mysongs.GeneralClasses.Global.playListSelected;

public class NewPlayer extends AppCompatActivity implements View.OnClickListener {
    public static PreferencesHandler preferencesHandler;
    public static YoutubePlayerView youtubePlayerView;
    Button btn_audio, btn_video;
    TextView type;
    public static TextView song_title, song_album, song_duration;
    RecyclerView recyclerView;
    PlayerAdapter playListAdapter;
    static ImageView btn_play;
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
    }

    public static void loadVideo(String videoId) {
        Global.isPlay = false;
        btn_play.setImageResource(R.drawable.play_icon);
        YTParams params = new YTParams();
        params.setAutoplay(1);
//        params.setPlaybackQuality(PlaybackQuality.small);
//        youtubePlayerView.setMinimumHeight(Global.height);
        //  params.setControls(1);
        // initialize YoutubePlayerCallBackListener and VideoID
        youtubePlayerView.initialize(videoId, params, new YoutubePlayerView.YouTubeListener() {

            @Override
            public void onReady() {
                // when player is ready.
            }

            @Override
            public void onStateChange(YoutubePlayerView.STATE state) {
                String mystate = state.toString();
                if (mystate.equals("UNSTARTED") && preferencesHandler.getPlayer().equals("audio")) {
                    Log.d("StateChecker", "" + state);
                    //     btn_play.setVisibility(View.VISIBLE);
                    //   btn_play.setImageResource(R.drawable.play_icon);
                    videoImg.setVisibility(View.VISIBLE);
                    //   youtubePlayerView.setVisibility(View.INVISIBLE);
                    //   videoImg.setBackgroundResource(R.drawable.logo_icon);
                }
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

        // psuse video
        //    youtubePlayerView.pause();
        // play video when it's ready
        //   youtubePlayerView.play();


    }

    private void init() {
        type = findViewById(R.id.type);

        preferencesHandler = new PreferencesHandler(NewPlayer.this);
        btn_play = findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        youtubePlayerView = (YoutubePlayerView) findViewById(R.id.youtubePlayerView);


// make auto height of youtube. if you want to use 'wrap_content'
        //youtubePlayerView.setAutoPlayerHeight(this);

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
        if (!Global.videoCode.equals("0") && !Global.videoCode.equals("")) {
            loadVideo(Global.videoCode);
        }


        if (preferencesHandler.getPlayer().equals("audio")) {
            type.setText("Audio");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                btn_audio.setBackgroundColor(getColor(R.color.gray));
                btn_video.setBackgroundColor(getColor(R.color.purple_700));
            }
            btn_play.setVisibility(View.VISIBLE);
            btn_play.setImageResource(R.drawable.play_icon);
            videoImg.setVisibility(View.VISIBLE);
        } else {
            type.setText("Video");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                btn_video.setBackgroundColor(getColor(R.color.gray));
                btn_audio.setBackgroundColor(getColor(R.color.purple_700));
            }
            btn_play.setVisibility(View.GONE);
            videoImg.setVisibility(View.GONE);
        }
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
                type.setText("Audio");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btn_audio.setBackgroundColor(getColor(R.color.gray));
                    btn_video.setBackgroundColor(getColor(R.color.purple_700));
                }
              //  youtubePlayerView.pause();
                Global.isPlay = false;
                preferencesHandler.setPlayer("audio");
                btn_play.setVisibility(View.VISIBLE);
//                videoImg.setVisibility(View.VISIBLE);
                btn_play.setImageResource(R.drawable.play_icon);
                break;
            case R.id.btn_video:
                type.setText("Video");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btn_video.setBackgroundColor(getColor(R.color.gray));
                    btn_audio.setBackgroundColor(getColor(R.color.purple_700));
                }
               // youtubePlayerView.pause();
                Global.isPlay = false;
                preferencesHandler.setPlayer("video");
                btn_play.setVisibility(View.GONE);
                videoImg.setVisibility(View.GONE);
                break;
            case R.id.btn_play:
                if (Global.isPlay) {
                    Global.isPlay = false;
                    btn_play.setImageResource(R.drawable.play_icon);
                    youtubePlayerView.pause();
                } else {
                    Global.isPlay = true;
                    btn_play.setImageResource(R.drawable.pause_icon);
                    youtubePlayerView.play();
                }
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