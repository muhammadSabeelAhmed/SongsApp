package app.sabeeldev.mysongs.Activities;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import app.sabeeldev.mysongs.Adpater.PlayerAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.GeneralClasses.MyBrowser;
import app.sabeeldev.mysongs.R;

import static app.sabeeldev.mysongs.GeneralClasses.Global.playListSelected;

public class Player extends AppCompatActivity implements View.OnClickListener {
    Button btn_audio, btn_video;
    TextView type;
    public static TextView song_title, song_album, song_duration;
    RecyclerView recyclerView;
    PlayerAdapter playListAdapter;
    TextView title;
    // url of video which we are loading.
    public WebView videoView;
    public static ImageView videoImg;
    Handler myHandler;
    Runnable myRunnable;
    String frameVideo = "";
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        init();
    }


    private void init() {
        Global.mKProgressHUD = KProgressHUD.create(Player.this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setDimAmount(0.7f).setAnimationSpeed(2).setLabel("Loading Song\nPlease wait").setCancellable(true);
        Global.mKProgressHUD.show();
        btn_audio = findViewById(R.id.btn_audio);
        btn_audio.setOnClickListener(this);

        btn_video = findViewById(R.id.btn_video);
        btn_video.setOnClickListener(this);

        song_title = findViewById(R.id.playedSong_title);
        song_title.setText(Global.videoTitle);
        type = findViewById(R.id.type);
        playListAdapter = new PlayerAdapter();
        song_album = findViewById(R.id.playedSong_album);
        song_duration = findViewById(R.id.playedSong_duration);
        title = findViewById(R.id.player_title);

        title.setText(playListSelected);
        song_duration.setText("");
        song_album.setText(playListSelected);

        videoImg = findViewById(R.id.videoimage);
        recyclerView = findViewById(R.id.player_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(Player.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(playListAdapter);
        Log.d("MyfavSize", "" + Global.favList.size());

        for (int i = 0; i < Global.playList.size(); i++) {
            if (Global.playList.get(i).equals(playListSelected)) {
                playListAdapter.addAllItems(Global.sortedList.get(i).getMysongs());
                playListAdapter.notifyDataSetChanged();
            }
        }
        videoView = findViewById(R.id.video_view);


        myHandler = new Handler();
        myRunnable = new Runnable() {
            @Override
            public void run() {
                if (!Global.duration.equals("")) {
                    startLoadingPage("video");
                } else {
                    myHandler.postDelayed(myRunnable, 800);
                }
            }
        };
        myHandler.post(myRunnable);
        Picasso.get().load(Global.imgURL).into(videoImg);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_audio:
                startLoadingPage("audio");
                break;
            case R.id.btn_video:
                startLoadingPage("video");
                break;
        }
    }

    public void startLoadingPage(String type) {
        for (int i = 0; i < Global.videosFormats.size(); i++) {
            if (Global.videosFormats.get(i).getType().equals("mp4")) {
                if (Global.videosFormats.get(i).getQuality().equals("360")) {
                    Global.TEST_URL_MP4 = Global.videosFormats.get(i).getLink();
                }
            } else if (Global.videosFormats.get(i).getType().equals("audio")) {
                if (Global.videosFormats.get(i).getExtension().equals("m4a") || Global.videosFormats.get(i).getExtension().equals("opus") || Global.videosFormats.get(i).getExtension().equals("mp3")) {
                    Global.TEST_URL_MP3 = Global.videosFormats.get(i).getLink();
                }
            }
        }
        WebViewClient webClient = new WebViewClient() {
            // Override page so it's load on my view only
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Global.mKProgressHUD.dismiss();
            }
        };
        videoView.setWebViewClient(webClient);
        videoView.getSettings().setLoadsImagesAutomatically(true);
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (type.equals("video")) {
            this.type.setText("Video");
            frameVideo = "<!DOCTYPE html><html><body style=\"padding: 0px; margin: 0px;\"><iframe width=100% height=250px src=\"" + Global.TEST_URL_MP4 + "\" frameborder=\"0\" allowfullscreen=true></iframe></body></html>";
        } else if (type.equals("audio")) {
            this.type.setText("Audio");
            frameVideo = "<!DOCTYPE html><html><body style=\"padding: 0px; margin: 0px;\"><iframe width=100% height=250px src=\"" + Global.TEST_URL_MP3 + "\" frameborder=\"0\" allowfullscreen=true></iframe></body></html>";
        }
        Player.videoImg.setVisibility(View.GONE);
        videoView.setVisibility(View.VISIBLE);
        videoView.loadData(frameVideo, "text/html", "utf-8");
    }
}