package directedstudy.georgiacentral.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import directedstudy.georgiacentral.R;

/**
 * Represents the Base Activity after logging in
 */
public class Navigator extends AppCompatActivity {
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
    }
}

