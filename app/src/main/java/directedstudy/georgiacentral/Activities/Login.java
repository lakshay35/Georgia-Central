package directedstudy.georgiacentral.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import directedstudy.georgiacentral.Email.EmailUtility;
import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.UserSchema;

public class Login extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private UserSchema userSchema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail     = (EditText) findViewById(R.id.etEmail);
        etPassword  = (EditText) findViewById(R.id.etPassword);
        userSchema  = new UserSchema(this);
    }//onCreate

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

        String email    = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User user       = new User(email, password);
        User dbUser     = userSchema.logIn(user);

        if(dbUser != null) {
            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();

            SessionManager sessionManager = new SessionManager(getApplicationContext());

            sessionManager.createLoginSession(dbUser.getFirstName()+dbUser.getLastName(), dbUser.getEmail());

            Intent intent = new Intent(this, Navigator.class);

            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_LONG).show();
    }//onClickLogin

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }//onClickRegister

    public void onClickForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }//onClickForgotPassword

    public void onClickChangePassword(View view) {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }//onClickChangePassword

}//class
