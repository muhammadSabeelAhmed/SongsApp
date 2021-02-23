package app.sabeeldev.mysongs.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DbViewmModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Favourite>> getFavourites;
    private LiveData<List<Recent>> getRecents;

    public DbViewmModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        getFavourites = repository.getAllFav();
        getRecents = repository.getAllRecent();
    }

    public void insertRecent(Recent recent) {
        repository.insertRecent(recent);
    }

    public void updateRecent(Recent recent) {
        repository.updateRecent(recent);
    }

    public void clearRecent() {
        repository.clearRecent();
    }

    public LiveData<List<Recent>> getAllRecents() {
        return getRecents;
    }

    public void insertFav(Favourite favourite) {
        repository.insertFav(favourite);
    }

    public void udpateFav(Favourite favourite) {
        repository.updateFav(favourite);
    }

    public void clearFav() {
        repository.clearFav();
    }
    public void deleteSingleFav(String title) {
        repository.deleteSingleFav(title);
    }
    public String checkIfExistFav(String title) {
      return   repository.checkIfExistFav(title);

    }

    public LiveData<List<Favourite>> getAllFav() {
        return getFavourites;
    }
}
