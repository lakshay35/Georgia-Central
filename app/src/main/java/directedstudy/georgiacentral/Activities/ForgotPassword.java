package directedstudy.georgiacentral.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import directedstudy.georgiacentral.Email.EmailUtility;
import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

/**
 * Represents the ForgotPassword view and its functionalities
 */
public class ForgotPassword extends AppCompatActivity {

    EditText etEmail;
    /***
     * Sets up the ForgotPassword View and retrieves objects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setTitle(R.string.forgot_password);
        etEmail = findViewById(R.id.etEmail);
    }

    /**
     * Submit Button Listener
     * Makes a DB request to update User's password with a temporary password
     * @param view
     */
    public void onSubmitClick(View view) {
        UserSchema userSchema = new UserSchema(this);
        User user = userSchema.retrieveUser(new User(etEmail.getText().toString().trim()));
        final String password = getTempPassword();
        user.setPassword(password);
        userSchema.updateUser(user);

        final ProgressDialog progressDialog = new ProgressDialog(ForgotPassword.this);
        progressDialog.setTitle("Requesting New Password...");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EmailUtility email = new EmailUtility();
                    email.sendMail("Forgot Password Request",
                            "Hi seems like you forgot your password. Therefore we have generated a temporary password.\n\n" +
                                    "Your new password is " + password + ".\nYou can go ahead and reset it on our app.",
                            "GeorgiaCentral",
                            etEmail.getText().toString());
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        thread.start();

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    /**
     * Creates a temporary password
     * @return temporary password
     */
    private String getTempPassword() {
        String [] characters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
                "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!",
                "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-"};
        StringBuilder sb = new StringBuilder(12);
        for(int i = 0; i < 12; i++) {
            Random random = new Random();
            sb.append(characters[random.nextInt(characters.length)]);
        }
        return sb.toString();
    }

    /**
     * OnClick Listener for cancel button
     * @param view
     */
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
