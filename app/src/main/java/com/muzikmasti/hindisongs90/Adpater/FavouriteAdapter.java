package com.muzikmasti.hindisongs90.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

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

import static com.muzikmasti.hindisongs90.Fragments.RecentFragment.recentAdapter;
import static com.muzikmasti.hindisongs90.GeneralClasses.Global.playerChecker;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    List<Favourite> favouriteList;
    Context context;
    PostWebAPIData postWebAPIData;

    public FavouriteAdapter() {
        postWebAPIData = new PostWebAPIData();
        favouriteList = new ArrayList<>();
    }

    public void setFavourite(List<Favourite> favourite) {
        this.favouriteList = favourite;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.fav_rec_items, parent, false);
        return new FavouriteAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
        holder.txt_title.setText(favouriteList.get(position).getTitle());
        holder.txt_album.setText(favouriteList.get(position).getAlbumName());
        Picasso.get().load(favouriteList.get(position).getImage()).into(holder.img);
        holder.img_remove.setImageResource(R.drawable.ic_fav_selected);
        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewModel.deleteSingleFav(favouriteList.get(position).getTitle());
                for (int i = 0; i < Global.favList.size(); i++) {
                    if (Global.favList.get(i).getTitle().equals(favouriteList.get(position).getTitle())) {
                        Global.favList.remove(i);
                        break;
                    }
                }
                recentAdapter.setRecent(Global.recentList);
            }
        });

        holder.playlist_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChecker="fav";
                Recent recent = new Recent("" + favouriteList.get(position).getAlbumID(), "" + favouriteList.get(position).getAlbumName(), "" + favouriteList.get(position).getAlbumsort(),
                        "" + favouriteList.get(position).getSongID(), "" + favouriteList.get(position).getTitle(), "" + favouriteList.get(position).getWebview(),
                        "" + favouriteList.get(position).getIsRedirection(), "" + favouriteList.get(position).getRedirectionApp(), "" + favouriteList.get(position).getImage(),
                        "" + favouriteList.get(position).getYoutubecode(), "" + favouriteList.get(position).getSongSortorder());
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

                Global.playListSelected = favouriteList.get(position).getAlbumName();
                Global.imgURL = favouriteList.get(position).getImage();
                Global.videoTitle = favouriteList.get(position).getTitle();
                Global.videoCode = favouriteList.get(position).getYoutubecode();
                Global.duration = "";
              //  postWebAPIData.GetVideoData(favouriteList.get(position).getYoutubecode(), context);
                  Global.changeActivity(context, new NewPlayer());
            }
        });


    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_title, txt_album;
        ImageView img_remove;
        RelativeLayout playlist_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.favRecSong_img);
            img_remove = itemView.findViewById(R.id.favRecSong_remove);
            txt_title = itemView.findViewById(R.id.favRecSong_title);
            txt_album = itemView.findViewById(R.id.favRecSong_album);
            playlist_card = itemView.findViewById(R.id.playlist_card);
        }
    }
}
