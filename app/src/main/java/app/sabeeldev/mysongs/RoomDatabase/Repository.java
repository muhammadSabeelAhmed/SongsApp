package app.sabeeldev.mysongs.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    RecentDao recentDao;
    FavouriteDao favouriteDao;

    private LiveData<List<Recent>> recents;
    private LiveData<List<Favourite>> favourite;

    public Repository(Application application) {
        RecentDatabase recentDatabase = RecentDatabase.getInstance(application);

        recentDao = recentDatabase.recentDao();
        favouriteDao = recentDatabase.favouriteDao();

        recents = recentDao.showAllRecent();
        favourite = favouriteDao.showAllFavourite();
    }

    public void insertRecent(Recent recent) {
        new insertRecentAsyncTask(recentDao).execute(recent);
    }

    public void updateRecent(Recent recent) {
        new updateRecentAsyncTask(recentDao).execute(recent);
    }

    public void clearRecent() {
        new deleteRecentAsyncTask(recentDao).execute();
    }

    public LiveData<List<Recent>> getAllRecent() {
        return recents;
    }

    public LiveData<List<Favourite>> getAllFav() {
        return favourite;
    }

    public void insertFav(Favourite favourite) {
        new insertFavAsyncTask(favouriteDao).execute(favourite);
    }

    public void updateFav(Favourite favourite) {
        new updateFavAsyncTask(favouriteDao).execute(favourite);
    }

    public void clearFav() {
        new deleteFavAsyncTask(favouriteDao).execute();
    }
    public void deleteSingleFav(String title) {
        new deleteSingleFavAsyncTask(favouriteDao).execute(title);
    }

    public void checkIfExistFav(String title) {
        new checkIfExistFavAsyncTask(favouriteDao).execute(title);
    }


    private class insertRecentAsyncTask extends AsyncTask<Recent, Void, Void> {
        RecentDao recentDao;

        public insertRecentAsyncTask(RecentDao recentDao) {
            this.recentDao = recentDao;
        }

        @Override
        protected Void doInBackground(Recent... clients) {
            recentDao.insertRecent(clients[0]);
            return null;
        }
    }

    private class updateRecentAsyncTask extends AsyncTask<Recent, Void, Void> {
        RecentDao recentDao;

        public updateRecentAsyncTask(RecentDao recentDao) {
            this.recentDao = recentDao;
        }

        @Override
        protected Void doInBackground(Recent... clients) {
            recentDao.insertRecent(clients[0]);
            return null;
        }
    }

    private class deleteRecentAsyncTask extends AsyncTask<Void, Void, Void> {
        RecentDao recentDao;

        public deleteRecentAsyncTask(RecentDao recentDao) {
            this.recentDao = recentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recentDao.clearAllRecent();
            return null;
        }
    }


    private class insertFavAsyncTask extends AsyncTask<Favourite, Void, Void> {
        FavouriteDao favouriteDao;

        public insertFavAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favouriteDao.insertFavourite(favourites[0]);
            return null;
        }
    }

    private class updateFavAsyncTask extends AsyncTask<Favourite, Void, Void> {
        FavouriteDao favouriteDao;

        public updateFavAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favouriteDao.updateFavourite(favourites[0]);
            return null;
        }
    }

    private class deleteFavAsyncTask extends AsyncTask<Void, Void, Void> {
        FavouriteDao favouriteDao;

        public deleteFavAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favouriteDao.clearAllFavourite();
            return null;
        }
    }

    private class deleteSingleFavAsyncTask extends AsyncTask<String, Void, Void> {
        FavouriteDao favouriteDao;

        public deleteSingleFavAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            favouriteDao.deleteSingleFav(strings[0]);
            return null;
        }
    }


    private class checkIfExistFavAsyncTask extends AsyncTask<String, Void, Boolean> {
        FavouriteDao favouriteDao;

        public checkIfExistFavAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return  favouriteDao.checkIfExistFav(strings[0]);
        }
    }
}
