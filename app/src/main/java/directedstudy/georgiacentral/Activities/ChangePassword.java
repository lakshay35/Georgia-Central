package directedstudy.georgiacentral.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

/**
 * Represents the ChangePassword view and its functionalities
 */
public class ChangePassword extends AppCompatActivity {

    EditText etEmail;
    EditText etOldPass;
    EditText etNewPass;

    /***
     * Sets up the ChangePassword View and retrieves objects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        etEmail = findViewById(R.id.etEmail);
        etOldPass = findViewById(R.id.etOldPass);
        etNewPass = findViewById(R.id.etNewPass);
    }

    /**
     * Submit Button Listener
     * Makes a DB request to update User's password with a new password
     * @param view
     */
    public void onClickSubmit(View view) {
        if(etOldPass.getText().toString().equals(etNewPass.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter a password different from your current one", Toast.LENGTH_LONG).show();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(ChangePassword.this);
            progressDialog.setTitle("Changing Your Password..");
            progressDialog.setMessage("Please wait");
            progressDialog.show();
            User user = new User(etEmail.getText().toString());
            UserSchema userSchema = new UserSchema(this);
            user = userSchema.retrieveUser(user);
            user.setPassword(etNewPass.getText().toString());
            userSchema.updateUser(user);
            Toast.makeText(getApplicationContext(), "Updated Password", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }
}
