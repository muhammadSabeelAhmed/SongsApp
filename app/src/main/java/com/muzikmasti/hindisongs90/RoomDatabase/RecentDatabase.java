package com.muzikmasti.hindisongs90.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { Favourite.class,Recent.class}, version = 1)
public abstract class RecentDatabase extends RoomDatabase {
    public static RecentDatabase instance;

    public abstract RecentDao recentDao();

    public abstract FavouriteDao favouriteDao();

    public static synchronized RecentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecentDatabase.class, "oldhindisongs_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }

    public static final RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
