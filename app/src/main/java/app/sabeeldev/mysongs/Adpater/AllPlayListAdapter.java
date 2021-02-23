package app.sabeeldev.mysongs.Adpater;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.Activities.MainActivity;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.Favourite;

public class AllPlayListAdapter extends RecyclerView.Adapter<AllPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList.Songs> newsongsPlayList;

    public AllPlayListAdapter() {
        newsongsPlayList = new ArrayList<>();
    }

    public void addAllItems(List<PlayList.Songs> inners) {
        newsongsPlayList.clear();
        newsongsPlayList.addAll(inners);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllPlayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.all_playlist_items, parent, false);
        return new AllPlayListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlayListAdapter.ViewHolder holder, int position) {
        //  if (newsongsPlayList.get(position).getAlbumName().equals(currentPlayList)) {
        holder.playlist_card.setVisibility(View.VISIBLE);
        Picasso.get().load(newsongsPlayList.get(position).getImage()).into(holder.song_img);
        holder.song_title.setText(newsongsPlayList.get(position).getTitle());
        holder.song_album.setText(newsongsPlayList.get(position).getAlbumName());
        Log.d("MyFavourite", "MySize" + Global.favList.size());
        for (int i = 0; i < Global.favList.size(); i++) {
            if (Global.favList.get(i).getTitle().equals(newsongsPlayList.get(position).getTitle())) {
                Log.d("MyFavourite", "" + newsongsPlayList.get(position).getTitle());
                holder.fav.setImageResource(R.drawable.ic_fav_selected);
//                Global.favList.add(new Favourite("" + newsongsPlayList.get(position).getAlbumID(), "" + newsongsPlayList.get(position).getAlbumName(), "" + newsongsPlayList.get(position).getAlbumsort(),
//                        "" + newsongsPlayList.get(position).getSongID(), "" + newsongsPlayList.get(position).getTitle(), "" + newsongsPlayList.get(position).getWebview(),
//                        "" + newsongsPlayList.get(position).getIsRedirection(), "" + newsongsPlayList.get(position).getRedirectionApp(), "" + newsongsPlayList.get(position).getImage(),
//                        "" + newsongsPlayList.get(position).getYoutubecode(), "" + newsongsPlayList.get(position).getSongSortorder()));
            }
        }
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fav.setImageResource(R.drawable.ic_fav_selected);
                String valuse = MainActivity.viewModel.checkIfExistFav(newsongsPlayList.get(position).getTitle());
                Log.d("AlreadyFound", "values" + valuse);
                if (valuse == null || valuse.equals("")) {
                    MainActivity.viewModel.insertFav(new Favourite("" + newsongsPlayList.get(position).getAlbumID(), "" + newsongsPlayList.get(position).getAlbumName(), "" + newsongsPlayList.get(position).getAlbumsort(),
                            "" + newsongsPlayList.get(position).getSongID(), "" + newsongsPlayList.get(position).getTitle(), "" + newsongsPlayList.get(position).getWebview(),
                            "" + newsongsPlayList.get(position).getIsRedirection(), "" + newsongsPlayList.get(position).getRedirectionApp(), "" + newsongsPlayList.get(position).getImage(),
                            "" + newsongsPlayList.get(position).getYoutubecode(), "" + newsongsPlayList.get(position).getSongSortorder()));
                } else {
                    Toast.makeText(context, "Already Added to Favourite", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        } else {
//            holder.playlist_card.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return newsongsPlayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView song_img, fav;
        TextView song_title, song_album;
        RelativeLayout playlist_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_img = itemView.findViewById(R.id.playlistSong_img);
            song_title = itemView.findViewById(R.id.playlistSong_title);
            song_album = itemView.findViewById(R.id.playlistSong_album);
            playlist_card = itemView.findViewById(R.id.playlist_card);
            fav = itemView.findViewById(R.id.playlistSong_fav);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.playlistSong_fav:
//
//                    break;
//                //  Toast.makeText(context, song_title.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
        }
    }
}

