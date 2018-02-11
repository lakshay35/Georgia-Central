package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import directedstudy.georgiacentral.R;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    /**
     * Transfers user to the PostTextBook activity
     * @param view
     */
    public void onClickPostTextBook(View view) {
        Intent intent = new Intent(this, PostTextbook.class);
        startActivity(intent);
    }

    /**
     * Transfers user to the Profile activity
     * @param view
     */
    public void onClickProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void onClickSearch(View view){
        Intent intent = new Intent(this, TextbookSearch.class);
        startActivity(intent);
    }//onClickSearch

}//class

