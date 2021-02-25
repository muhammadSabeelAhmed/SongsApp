package app.sabeeldev.mysongs.Activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

import app.sabeeldev.mysongs.Fragments.MainFragment;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.DbViewmModel;
import app.sabeeldev.mysongs.RoomDatabase.Favourite;
import app.sabeeldev.mysongs.RoomDatabase.Recent;

public class MainActivity extends AppCompatActivity {
    public static DbViewmModel viewModel;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Global.height = displayMetrics.heightPixels;
        Global.width = displayMetrics.widthPixels;
        Log.d("HxW", "" + Global.height + "--" + Global.width);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(MainActivity.this).get(DbViewmModel.class);
        }
        favouriteHandler();
        recentHandler();
        Global.changeFragmentMain(MainActivity.this, new MainFragment(), "MainFragment", false);
    }


    public void favouriteHandler() {
        MainActivity.viewModel.getAllFav().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                Global.favList = favourites;
            }
        });
    }

    public void recentHandler() {
        MainActivity.viewModel.getAllRecents().observe(this, new Observer<List<Recent>>() {
            @Override
            public void onChanged(List<Recent> recents) {
                Global.recentList = recents;
            }
        });
    }
}