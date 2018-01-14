package directedstudy.georgiacentral;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Navigator extends AppCompatActivity {
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        setupNavigationBar();
    }

    protected void setupNavigationBar() {
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            pushFragment(new Browse());
            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item, bottomNavigationView);
                            return false;
                        }
                    }
            );
        }
    }

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
                pushFragment(new Profile());
                break;
            case R.id.chat:
                pushFragment(new Chat());
                break;
            case R.id.sell:
                pushFragment();
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

