package directedstudy.georgiacentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, Navigator.class);
        intent.putExtra("go", "go");
        startActivity(intent);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, Registration.class);

        intent.putExtra("go", "go");
        startActivity(intent);
    }//onClickRegister
}