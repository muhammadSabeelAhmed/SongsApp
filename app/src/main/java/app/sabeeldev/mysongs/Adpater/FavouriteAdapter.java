package app.sabeeldev.mysongs.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.Favourite;
import app.sabeeldev.mysongs.RoomDatabase.Recent;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    List<Favourite> favouriteList;
    Context context;

    public FavouriteAdapter() {
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
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_remove, txt_title, txt_album;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.favRecSong_img);
            txt_remove = itemView.findViewById(R.id.favRecSong_remove);
            txt_title = itemView.findViewById(R.id.favRecSong_title);
            txt_album = itemView.findViewById(R.id.favRecSong_album);
        }
    }
}
