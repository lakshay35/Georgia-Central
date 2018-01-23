package directedstudy.georgiacentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import directedstudy.georgiacentral.Objects.User;
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
        }else
            Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_LONG).show();
    }//login

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }//onClickRegister

}//class
