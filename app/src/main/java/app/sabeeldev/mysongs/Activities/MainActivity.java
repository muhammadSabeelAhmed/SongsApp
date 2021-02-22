package app.sabeeldev.mysongs.Activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import app.sabeeldev.mysongs.Fragments.MainFragment;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.DbViewmModel;

public class MainActivity extends AppCompatActivity {
    public static DbViewmModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(MainActivity.this).get(DbViewmModel.class);
        }
        //  viewModel.insertFav(new Favourite("", "Hiritk Roshan", "", "", "this is my song", "", "", "", "https://image.shutterstock.com/image-illustration/3d-illustration-musical-notes-signs-260nw-761313844.jpg", "", ""));
        Global.changeFragmentMain(MainActivity.this, new MainFragment(), "MainFragment", false);

        // mainAdapter = new MainAdapter();

//        mainAdapter.(Global.sortedList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        recyclerView.setAdapter(mainAdapter);
    }


//    @Override
//    public void onBackPressed() {
//        if (Global.back_status) {
//            getSupportFragmentManager().popBackStackImmediate();
//            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//        } else if (getSupportFragmentManager().getFragments().size() > 0) {
//            // super.onBackPressed();
//        }
//    }


}