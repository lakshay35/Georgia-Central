package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

public class Profile extends AppCompatActivity {

    ImageView profilePicture;
    Button logoutButton;
    TextView name;
    TextView phone;
    TextView email;
    SessionManager sessionManager;
    UserSchema userSchema;

    /**
     * Creates a Profile Page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profilePicture = findViewById(R.id.profilePicture);
        logoutButton = findViewById(R.id.logoutButton);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        userSchema = new UserSchema(this);

        sessionManager = new SessionManager(this);

        populateProfilePage();
    }

    /**
     * Populates Profile Page with user's details
     */
    private void populateProfilePage() {
        User user = userSchema.retrieveUser(sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL));
        name.setText(user.getFirstName() + " " + user.getLastName());
        phone.setText(user.getPhoneNumber());
        email.setText(user.getEmail());
    }

    /**
     * Logs user out
     * @param view
     */
    public void onClickLogout(View view) {
        sessionManager.logoutUser();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    /**
     * Edit User Profile
     * @param view
     */
    public void onClickEdit(View view) {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
}
