package directedstudy.georgiacentral.Activities;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import directedstudy.georgiacentral.Email.EmailUtility;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Session.SessionManager;

public class TextbookProfile extends AppCompatActivity {

    TextView tvBookTitle;
    TextView tvAuthor;
    TextView tvCourseNumber;
    TextView tvCondition;
    TextView tvPrice;
    TextView tvName;
    TextView tvEmail;
    TextView tvPhoneNumber;
    TextView tvPostDate;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle           = getIntent().getExtras();
        sessionManager          = new SessionManager(getApplicationContext());
        tvBookTitle             = (TextView) findViewById(R.id.tvBookTitle);
        tvAuthor                = (TextView) findViewById(R.id.tvAuthor);
        tvCourseNumber          = (TextView) findViewById(R.id.tvCourseNumber);
        tvCondition             = (TextView) findViewById(R.id.tvCondition);
        tvPrice                 = (TextView) findViewById(R.id.tvPrice);
        tvName                  = (TextView) findViewById(R.id.tvName);
        tvEmail                 = (TextView) findViewById(R.id.tvEmail);
        tvPhoneNumber           = (TextView) findViewById(R.id.tvPhoneNumber);
        tvPostDate              = (TextView) findViewById(R.id.tvPostDate);

        tvBookTitle.setText(bundle.getString("bookTitle"));
        tvAuthor.setText("Author: " + bundle.getString("author"));
        tvCourseNumber.setText("Course: " + bundle.getString("courseNumber"));
        tvCondition.setText("Condition: " +bundle.getString("condition"));
        DecimalFormat df = new DecimalFormat("#.00");
        NumberFormat nf = NumberFormat.getInstance();
        try {
            tvPrice.setText("$" + df.format(nf.parse(bundle.getString("price")).doubleValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvName.setText("Posted By: " + bundle.getString("name"));
        tvEmail.setText(bundle.getString("email"));
        tvPhoneNumber.setText(bundle.getString("phoneNumber"));
        tvPostDate.setText("Posted On: " + bundle.getString("postDate"));
    }//onCreate

    public void onClickSendEmail(View view){
        final ProgressDialog progressDialog = new ProgressDialog(TextbookProfile.this);
        progressDialog.setTitle("Sending Email...");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EmailUtility email = new EmailUtility();
                    email.sendMail("Purchase Request",
                            sessionManager.getUserDetails().get(SessionManager.KEY_NAME) + " ("+ sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL) + ") has requested to purchase \"" + tvBookTitle.getText() + "\"." +
                                    "\n\n\n Thank You, \n Georgia Central Team",
                            "GeorgiaCentral",
                            tvEmail.getText().toString());
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        thread.start();

        Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_LONG).show();
        tvEmail.setClickable(false);
        tvEmail.setTextColor(Color.GRAY);
    }//onClickSendEmail

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), TextbookSearch.class);
        startActivityForResult(myIntent, 0);

        return true;
    }//onOptionsItemSelected

}//class
