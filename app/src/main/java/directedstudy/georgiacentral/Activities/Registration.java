package directedstudy.georgiacentral.Activities;

import directedstudy.georgiacentral.Email.EmailUtility;
import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Represents the Registration Page
 */
public class Registration extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etPassword;
    EditText etPhoneNumber;
    UserSchema userSchema;

    /**
     * Sets up the Registration page and retrieves its objects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFirstName     = (EditText) findViewById(R.id.etFirstName);
        etLastName      = (EditText) findViewById(R.id.etLastName);
        etEmail         = (EditText) findViewById(R.id.etEmail);
        etPassword      = (EditText) findViewById(R.id.etPassword);
        etPhoneNumber   = (EditText) findViewById(R.id.etPhoneNumber);
        userSchema      = new UserSchema(this);

        setTitle("Register");
    }//onCreate

    /**
     * Submit Button Listener
     * Makes a Database request to create a user record
     * @param view
     */
    public void onClickSubmit(View view){
        final ProgressDialog mprogressDialog = new ProgressDialog(this);;

        mprogressDialog.setMessage("Creating User please wait...");
        mprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mprogressDialog.setIndeterminate(true);
        mprogressDialog.setCanceledOnTouchOutside(false);
        mprogressDialog.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mprogressDialog.dismiss();
            }//run

        }, 1000);

        User user = new User(etFirstName.getText().toString().trim(), etLastName.getText().toString().trim(), etEmail.getText().toString().trim(), etPassword.getText().toString().trim(), etPhoneNumber.getText().toString().trim());

        if(TextUtils.isEmpty(etFirstName.getText()) || TextUtils.isEmpty(etLastName.getText()) || TextUtils.isEmpty(etEmail.getText()) || TextUtils.isEmpty(etPassword.getText()) || TextUtils.isEmpty(etPhoneNumber.getText()))
            Toast.makeText(getApplicationContext(), "Please complete all fields", Toast.LENGTH_SHORT).show();
        else {
            if (etEmail.getText().toString().toLowerCase().indexOf("@uga.edu") == -1) {
                Toast.makeText(getApplicationContext(), "UGA Email Required", Toast.LENGTH_LONG).show();
            } else {
                boolean isRegistered = userSchema.addUser(user);

                if (isRegistered == true) {
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();

                    sendConfirmationEmail(); // Send email

                    Intent intent = new Intent(this, Login.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Error", Toast.LENGTH_LONG).show();
                }//if else
            }//if else
        }//if else
    }//onClickSubmit

    /**
     * Cancel Button Listener
     * Transfers user to the Login Activity
     * @param view
     */
    public void onClickCancel(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }//onClickCancel

    /**
     * Sends a confirmation email to the user's email address
     */
    private void sendConfirmationEmail() {
        final ProgressDialog progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setTitle("Sending Confirmation Email...");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EmailUtility email = new EmailUtility();
                    email.sendMail("Account Confirmation",
                            "You have successfully been registered. Thank you for joining Georgia Central.",
                            "GeorgiaCentral",
                            etEmail.getText().toString());
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        thread.start();
    }//SendConfirmationEmail
}
