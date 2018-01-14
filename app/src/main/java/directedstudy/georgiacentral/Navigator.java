package directedstudy.georgiacentral;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

public class Navigator extends AppCompatActivity {
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setupNavigationBar();
    }


    protected void setupNavigationBar() {
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            pushFragment(new Browse());
            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item, bottomNavigationView);
                            return false;
                        }
                    }
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void selectFragment(MenuItem item, BottomNavigationView bottomNavigationView) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.search:
                pushFragment(new Search());
                break;
            case R.id.browse:
                pushFragment(new Browse());
                break;
            case R.id.profile:
                Toolbar toolbar = findViewById(R.id.edit);
                setActionBar(toolbar);
                pushFragment(new Profile());
                break;
            case R.id.chat:
                pushFragment(new Chat());
                break;
            case R.id.sell:
                pushFragment(new Sell());
                break;
        }
    }


    protected void pushFragment(Fragment fragment) {
        if (fragment != fragment) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (fragmentTransaction != null) {
                fragmentTransaction.replace(R.id.rootLayout, fragment);
                fragmentTransaction.commit();
            }
        }
    }
}

