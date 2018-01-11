package directedstudy.georgiacentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void onClickSubmit(View View){
        Intent intent = new Intent(this, Navigator.class);

        intent.putExtra("go", "go");
        startActivity(intent);
    }//onClickSubmit

    public void onClickCancel(View view){
        Intent intent = new Intent(this, Login.class);

        intent.putExtra("go", "go");
        startActivity(intent);
    }//onClickCancel
}
