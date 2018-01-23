package directedstudy.georgiacentral;

import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.Tables.UserSchema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etPassword;
    EditText etPhoneNumber;
    UserSchema userSchema;

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

    public void onClickSubmit(View View){
        final ProgressDialog mDialog = new ProgressDialog(this);;

        mDialog.setMessage("Creating User please wait...");
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

        User user = new User(etFirstName.getText().toString(), etLastName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), etPhoneNumber.getText().toString());

        if(etEmail.getText().toString().toLowerCase().indexOf("@uga.edu") == -1)
            Toast.makeText(getApplicationContext(), "UGA Email Required", Toast.LENGTH_LONG).show();
        else{
            boolean isRegistered = userSchema.addUser(user);

            if(isRegistered == true) {
                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, Login.class);

                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Registration Error", Toast.LENGTH_LONG).show();
            }//if else
        }//if else
    }//onClickSubmit

    public void onClickCancel(View view){
        Intent intent = new Intent(this, Login.class);

        startActivity(intent);
    }//onClickCancel
}
