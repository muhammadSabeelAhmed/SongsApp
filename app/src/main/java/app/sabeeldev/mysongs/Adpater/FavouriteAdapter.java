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

import app.sabeeldev.mysongs.Activities.MainActivity;
import app.sabeeldev.mysongs.GeneralClasses.Global;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.favRecSong_img);
            img_remove = itemView.findViewById(R.id.favRecSong_remove);
            txt_title = itemView.findViewById(R.id.favRecSong_title);
            txt_album = itemView.findViewById(R.id.favRecSong_album);
        }
    }
}
