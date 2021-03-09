package com.muzikmasti.hindisongs90.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecentDao {

    @Insert
    void insertRecent(Recent recent);

    @Update
    void updateRecent(Recent recent);

    @Query("delete from recent_songs")
    void clearAllRecent();

    @Query("select * from recent_songs")
    LiveData<List<Recent>> showAllRecent();
}
