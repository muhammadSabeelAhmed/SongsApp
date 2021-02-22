package app.sabeeldev.mysongs.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavouriteDao {
    @Insert
    void insertFavourite(Favourite recent);

    @Update
    void updateFavourite(Favourite recent);

    @Query("delete from favourite_songs")
    void clearAllFavourite();

    @Query("select * from favourite_songs")
    LiveData<List<Favourite>> showAllFavourite();
}
