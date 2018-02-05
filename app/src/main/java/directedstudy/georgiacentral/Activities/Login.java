package directedstudy.georgiacentral.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

/***
 * Rpresents the Login page and its functionalities
 */
public class Login extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private UserSchema userSchema;

    /**
     * Sets up the Login page and retrieves its objects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail     = (EditText) findViewById(R.id.etEmail);
        etPassword  = (EditText) findViewById(R.id.etPassword);
        userSchema  = new UserSchema(this);
    }//onCreate

    /**
     * Login Button Listener
     * Makes a Database request to authenticate credentials
     * @param view
     */
    public void onClickLogin(View view) {
        final ProgressDialog mDialog = new ProgressDialog(this);;

        mDialog.setMessage("Logging in. Please wait...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDialog.dismiss();
            }//run

        }, 1000);

        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        User user       = new User(email, password);
        User dbUser     = userSchema.logIn(user);

        if(dbUser != null) {
            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();

            SessionManager sessionManager = new SessionManager(getApplicationContext());

            sessionManager.createLoginSession(dbUser.getFirstName()+dbUser.getLastName(), dbUser.getEmail());

            Intent intent = new Intent(this, PostTextbook.class);

            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_LONG).show();
    }//onClickLogin

    /**
     * Register Button Listener
     * Transfers user to the Registration Activity
     * @param view
     */
    public void onClickRegister(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }//onClickRegister

    /**
     * Forgot Password Button Listener
     * Transfers user to the ForgotPassword Activity
     * @param view
     */
    public void onClickForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }//onClickForgotPassword

    /**
     * Change Password Button Listener
     * Transfers user to the ChangePassword Activity
     * @param view
     */
    public void onClickChangePassword(View view) {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }//onClickChangePassword

}//class
