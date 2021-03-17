package com.muzikmasti.hindisongs90.Adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.muzikmasti.hindisongs90.Activities.MainActivity;
import com.muzikmasti.hindisongs90.Activities.NewPlayer;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RetrofitUtils.PostWebAPIData;
import com.muzikmasti.hindisongs90.RoomDatabase.Favourite;
import com.muzikmasti.hindisongs90.RoomDatabase.Recent;

import static com.muzikmasti.hindisongs90.GeneralClasses.Global.currentPosition;
import static com.muzikmasti.hindisongs90.GeneralClasses.Global.playerChecker;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {
    List<Recent> recentList;
    Context context;
    PostWebAPIData postWebAPIData;
    PreferencesHandler preferencesHandler;

    public RecentAdapter() {
        preferencesHandler = new PreferencesHandler();
        postWebAPIData = new PostWebAPIData();
        recentList = new ArrayList<>();
    }

    public void setRecent(List<Recent> recentList) {
        this.recentList = recentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.fav_rec_items, parent, false);
        return new RecentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_title.setText(recentList.get(position).getTitle());
        holder.txt_album.setText(recentList.get(position).getAlbumName());
        Picasso.get().load(recentList.get(position).getImage()).into(holder.img);
        holder.add_fav.setImageResource(R.drawable.ic_fav_unselect);

        for (int i = 0; i < Global.favList.size(); i++) {
            if (recentList.get(position).getTitle().equals(Global.favList.get(i).getTitle())) {
                holder.add_fav.setImageResource(R.drawable.ic_fav_selected);
            }
        }


        holder.add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!recentList.get(position).getYoutubecode().equals("0") && !recentList.get(position).getYoutubecode().equals("")) {
                    Favourite addfav = new Favourite("" + recentList.get(position).getAlbumID(), "" + recentList.get(position).getAlbumName(), "" + recentList.get(position).getAlbumsort(),
                            "" + recentList.get(position).getSongID(), "" + recentList.get(position).getTitle(), "" + recentList.get(position).getWebview(),
                            "" + recentList.get(position).getIsRedirection(), "" + recentList.get(position).getRedirectionApp(), "" + recentList.get(position).getImage(),
                            "" + recentList.get(position).getYoutubecode(), "" + recentList.get(position).getSongSortorder());

                    boolean check = false;
                    int index = 0;
                    for (int i = 0; i < Global.favList.size(); i++) {
                        if (Global.favList.get(i).getTitle().equals(addfav.getTitle())) {
                            check = true;
                            index = i;
                            break;
                        }
                    }
                    if (!check) {
                        holder.add_fav.setImageResource(R.drawable.ic_fav_selected);
                        MainActivity.viewModel.insertFav(addfav);
                        Global.favList.add(addfav);
                    } else {
                        MainActivity.viewModel.deleteSingleFav(recentList.get(position).getTitle());
                        holder.add_fav.setImageResource(R.drawable.ic_fav_unselect);
                        Global.favList.remove(index);
                    }
                } else {
                    Toast.makeText(context, "Invalid Video Code", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.playlist_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChecker = "recent";
                preferencesHandler.setCurrentPlaylist("recent");
                currentPosition = position;
                Recent recent = new Recent("" + recentList.get(position).getAlbumID(), "" + recentList.get(position).getAlbumName(), "" + recentList.get(position).getAlbumsort(),
                        "" + recentList.get(position).getSongID(), "" + recentList.get(position).getTitle(), "" + recentList.get(position).getWebview(),
                        "" + recentList.get(position).getIsRedirection(), "" + recentList.get(position).getRedirectionApp(), "" + recentList.get(position).getImage(),
                        "" + recentList.get(position).getYoutubecode(), "" + recentList.get(position).getSongSortorder());
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

                Global.playListSelected = recentList.get(position).getAlbumName();
                Global.imgURL = recentList.get(position).getImage();
                Global.videoTitle = recentList.get(position).getTitle();
                Global.duration = "";
                Global.videoCode = recentList.get(position).getYoutubecode();
                //  postWebAPIData.GetVideoData(recentList.get(position).getYoutubecode(), context);
                Global.changeActivity(context, new NewPlayer());
            }
        });

    }

    @Override
    public int getItemCount() {
        return recentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img, add_fav;
        TextView txt_title, txt_album;
        RelativeLayout playlist_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.favRecSong_img);
            add_fav = itemView.findViewById(R.id.favRecSong_remove);
            txt_title = itemView.findViewById(R.id.favRecSong_title);
            txt_album = itemView.findViewById(R.id.favRecSong_album);
            playlist_card = itemView.findViewById(R.id.playlist_card);
        }
    }
}
