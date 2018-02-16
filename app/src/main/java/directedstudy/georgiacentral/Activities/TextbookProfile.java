package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import directedstudy.georgiacentral.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle           = getIntent().getExtras();
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
        tvPrice.setText("$" + bundle.getString("price"));
        tvName.setText("Posted By: " + bundle.getString("name"));
        tvEmail.setText(bundle.getString("email"));
        tvPhoneNumber.setText(bundle.getString("phoneNumber"));
        tvPostDate.setText("Posted On: " + bundle.getString("postDate"));
    }//onCreate

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), TextbookSearch.class);
        startActivityForResult(myIntent, 0);

        return true;
    }//onOptionsItemSelected

}//class
