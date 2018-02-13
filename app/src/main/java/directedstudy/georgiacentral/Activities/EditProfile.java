package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

public class EditProfile extends AppCompatActivity {

    EditText editFname;
    EditText editLname;
    EditText editPhone;
    Button save;
    UserSchema userSchema;
    User user;
    SessionManager sessionManager;

    /**
     * Creates the EditProfile view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editFname = findViewById(R.id.editFname);
        editLname = findViewById(R.id.editLname);
        editPhone = findViewById(R.id.editPhone);
        save = findViewById(R.id.save);
        userSchema = new UserSchema(this);

        sessionManager = new SessionManager(this);
        user = userSchema.retrieveUser(sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL));

        populateData();
    }

    /**
     * Populates the view with user data
     */
    private void populateData() {
        editFname.setText(user.getFirstName());
        editLname.setText(user.getLastName());
        editPhone.setText(user.getPhoneNumber());
    }

    /**
     * Saves User info in the database
     * @param view
     */
    public void onClickSave(View view) {
        user.setFirstName(editFname.getText().toString());
        user.setLastName(editLname.getText().toString());
        user.setPhoneNumber(editPhone.getText().toString());
        userSchema.updateUser(user);

        sessionManager.updateUserInfo(user.getFirstName() + " " + user.getLastName());
        Toast.makeText(getApplicationContext(), "Updated Profile    ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    /**
     * Returns user to profile page
     * @param view
     */
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
