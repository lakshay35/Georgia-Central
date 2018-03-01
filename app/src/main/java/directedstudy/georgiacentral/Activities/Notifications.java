package directedstudy.georgiacentral.Activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.Textbook;
import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.Tables.NotificationSchema;
import directedstudy.georgiacentral.Tables.TextBookSchema;
import directedstudy.georgiacentral.Tables.UserSchema;

public class Notifications extends AppCompatActivity {

    String[] spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextBookSchema textBookSchema = new TextBookSchema(this);
        ArrayList<Textbook> textbooks = textBookSchema.retrieveAllTextbooks();
        spinner = new String[textbooks.size()];
        populateSpinner(textbooks);
    }

    /**
     * Populates spinner with all the books in the TextBook Table
     * @param textbooks
     */
    public void populateSpinner(ArrayList<Textbook> textbooks) {
        for(int i = 0; i < textbooks.size(); i++) {
            spinner[i] = textbooks.get(i).getBookTitle();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinner);
        MaterialBetterSpinner materialBetterSpinner = (MaterialBetterSpinner) findViewById(R.id.android_material_design_spinner);
        materialBetterSpinner.setAdapter(arrayAdapter);
    }

    /**
     * Adds notifications to the database
     * @param view
     */
    public void addNotification(View view) {
        NotificationSchema notificationSchema = new NotificationSchema(this);
        MaterialBetterSpinner materialBetterSpinner = (MaterialBetterSpinner) findViewById(R.id.android_material_design_spinner);

        SessionManager sessionManager = new SessionManager(this);

        UserSchema userSchema = new UserSchema(this);
        User user = userSchema.retrieveUser(sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL));


        if(notificationSchema.addNotification(materialBetterSpinner.getText().toString(), user.getUserID())) {
            Toast.makeText(this, "Added Notification", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Notification Exists Already", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Adds functionality to go back to homepage
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Homepage.class);
        startActivityForResult(myIntent, 0);

        return true;
    }
}

