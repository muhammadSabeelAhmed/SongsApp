package app.sabeeldev.mysongs.Adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.Activities.MainActivity;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RetrofitUtils.PostWebAPIData;
import app.sabeeldev.mysongs.RoomDatabase.Recent;

import static app.sabeeldev.mysongs.GeneralClasses.Global.playerChecker;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList.Songs> newsongsPlayList;
    PostWebAPIData postWebAPIData;

    public PlayListAdapter() {
        postWebAPIData = new PostWebAPIData();
        newsongsPlayList = new ArrayList<>();
    }

    public void addInner(List<PlayList.Songs> inners) {
        newsongsPlayList.clear();
        newsongsPlayList.addAll(inners);
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
        holder.playlist_card.setVisibility(View.VISIBLE);
        Picasso.get().load(newsongsPlayList.get(position).getImage()).into(holder.song_img);
        holder.song_title.setText(newsongsPlayList.get(position).getTitle());
        FrameLayout.LayoutParams mycurrentparams = new FrameLayout.LayoutParams((Global.height / 5) - 120, (Global.height / 5) - 120);
        holder.song_img.setLayoutParams(mycurrentparams);
        holder.song_title.setVisibility(View.VISIBLE);
        if (newsongsPlayList.get(position).getAlbumName().equals("Apps For You")) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((Global.width / 2) - 60, (Global.height / 5) - 60);
            holder.song_img.setLayoutParams(layoutParams);
            holder.song_title.setVisibility(View.GONE);
            //  holder.playlist_card.setBackgroundResource(R.color.orange_light);
        }
        holder.playlist_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChecker="normal";
                if (newsongsPlayList.get(position).getAlbumName().contains("Apps")) {
                    loadURL("https://play.google.com/store/apps/details?id=com.muzikmasti.oldhindivideosongs");
                } else {
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
                    Global.duration = "";
                    postWebAPIData.GetVideoData(newsongsPlayList.get(position).getYoutubecode(),context);
                  //  Global.changeActivity(context, new Player());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = 0;
        Log.d("MySongsSize", "" + newsongsPlayList.size());
        if (newsongsPlayList.size() >= 7) {
            size = 6;
        } else if (newsongsPlayList.size() <= 6) {
            size = newsongsPlayList.size();
        }
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView song_img;
        TextView song_title;
        RelativeLayout playlist_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_img = itemView.findViewById(R.id.playlistSong_img);
            song_title = itemView.findViewById(R.id.playlistSong_title);
            playlist_card = itemView.findViewById(R.id.playlist_card);
        }
    }

    public void loadURL(String url) {
        if (url != null && !url.equals("")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        }
    }
}
