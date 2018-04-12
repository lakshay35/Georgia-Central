package directedstudy.georgiacentral.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.Objects.Condition;
import directedstudy.georgiacentral.Objects.Course;
import directedstudy.georgiacentral.Objects.TextBookPost;
import directedstudy.georgiacentral.Objects.Textbook;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.ConditionSchema;
import directedstudy.georgiacentral.Tables.CourseSchema;
import directedstudy.georgiacentral.Tables.TextBookPostSchema;
import directedstudy.georgiacentral.Tables.TextBookSchema;
import directedstudy.georgiacentral.Tables.UserSchema;

public class PostTextbook extends AppCompatActivity implements OnItemSelectedListener {

    Button btnDelete;
    EditText etBookTitle;
    EditText etAuthor;
    EditText etCourseNumber;
    EditText etPrice;
    Spinner spCondition;
    ConditionSchema conditionSchema;
    TextBookSchema textBookSchema;
    CourseSchema courseSchema;
    TextBookPostSchema textBookPostSchema;
    SessionManager sessionManager;
    UserSchema userSchema;
    boolean isPosted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_textbook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnDelete           = (Button) findViewById(R.id.btnDelete);
        etBookTitle         = (EditText) findViewById(R.id.etBookTitle);
        etAuthor            = (EditText) findViewById(R.id.etAuthor);
        etCourseNumber      = (EditText) findViewById(R.id.etCourseNumber);
        etPrice             = (EditText) findViewById(R.id.etPrice);
        spCondition         = (Spinner) findViewById(R.id.spCondition);
        conditionSchema     = new ConditionSchema(this);
        textBookSchema      = new TextBookSchema(this);
        courseSchema        = new CourseSchema(this);
        textBookPostSchema  = new TextBookPostSchema(this);
        sessionManager      = new SessionManager(getApplicationContext());
        userSchema          = new UserSchema(this);

        spCondition.setOnItemSelectedListener(this);
        loadSpinnerData();

        if(getIntent().hasExtra("bookTitle")){
            Bundle bundle = getIntent().getExtras();

            btnDelete.setVisibility(View.VISIBLE);
            etBookTitle.setText(bundle.getString("bookTitle"));
            etAuthor.setText(bundle.getString("author"));
            etCourseNumber.setText(bundle.getString("courseNumber"));
            DecimalFormat df = new DecimalFormat("#.00");
            NumberFormat nf = NumberFormat.getInstance();
            try {
                etPrice.setText(df.format(nf.parse(bundle.getString("price")).doubleValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            spCondition.setSelection(conditionSchema.retrieveCondition(bundle.getString("condition")).getConditionID()-1);
        }//if
    }//onCreate

    public boolean onOptionsItemSelected(MenuItem item){
        super.onBackPressed();

        return true;
    }//onOptionsItemSelected

    public void onClickDelete(View view){
        new AlertDialog.Builder(this)
            .setTitle("Delete Post?")
            .setMessage("Are you sure you want to Delete?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    boolean isDeleted = textBookPostSchema.deleteTextBookPost(Integer.parseInt(getIntent().getExtras().getString("postID")));

                    if(isDeleted == true) {
                        Toast.makeText(getApplicationContext(), "Post Deleted", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PostTextbook.this, UserPostList.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot Delete", Toast.LENGTH_LONG).show();
                    }//if else
                }})
            .setNegativeButton(android.R.string.no, null).show();
    }//onClickDelete

    public void onClickSubmit(View view){
        final ProgressDialog mprogressDialog = new ProgressDialog(this);;

        mprogressDialog.setMessage("Creating Post please wait...");
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

        Textbook textbook               = new Textbook(etBookTitle.getText().toString().trim(), etAuthor.getText().toString().trim());
        Course course                   = new Course(etCourseNumber.getText().toString().trim());
        int textBookID                  = retrieveBookID(textbook);
        int courseID                    = retrieveCourseID(course);
        String condition                = spCondition.getSelectedItem().toString().trim();
        int conditionID                 = conditionSchema.retrieveCondition(condition).getConditionID();
        String email                    = sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL);
        int userID                      = userSchema.retrieveUser(email).getUserID();
        DateFormat df                   = new SimpleDateFormat("MM/dd/yyyy");
        Calendar today                  = Calendar.getInstance();
        Date date                       = today.getTime();
        String postDate                 = df.format(date);

        today.add(Calendar.MONTH,1);

        date                            = today.getTime();
        String expireDate               = df.format(date);
        TextBookPost textBookPost       = new TextBookPost(userID, textBookID, courseID, conditionID, etPrice.getText().toString(), expireDate, postDate);
        if(TextUtils.isEmpty(etBookTitle.getText()) || TextUtils.isEmpty(etAuthor.getText()) || TextUtils.isEmpty(etCourseNumber.getText()) || TextUtils.isEmpty(etPrice.getText()))
            Toast.makeText(getApplicationContext(), "Please complete all fields", Toast.LENGTH_SHORT).show();
        else {
            if (getIntent().hasExtra("bookTitle")) {
                textBookPost = new TextBookPost(Integer.parseInt(getIntent().getExtras().getString("postID")), userID, textBookID, courseID, conditionID, etPrice.getText().toString(), expireDate, postDate);
                isPosted = textBookPostSchema.updateTextBookPost(textBookPost);
            } else {
                isPosted = textBookPostSchema.addTextBookPost(textBookPost);
            }//if else

            if (isPosted == true) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, Homepage.class);

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }//if else
        }//if else
    }//onClickSubmit

    public void onClickCancel(View view){
        Intent intent = new Intent(this, Homepage.class);

        startActivity(intent);
    }//onClickCancel

    private void loadSpinnerData() {
        List<Condition> conditionList          = conditionSchema.getConditionList();
        List<String> conditionNames            = new ArrayList<>();

        for(Condition c : conditionList){
            conditionNames.add(c.getCondition());
        }//foreach

        ArrayAdapter<String> dataAdapter       = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conditionNames);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCondition.setAdapter(dataAdapter);
    }//loadSpinnerData

    public int retrieveCourseID(Course course){
        if(courseSchema.retrieveCourse(course) == null) {
            courseSchema.addCourse(course);

            return courseSchema.retrieveCourse(course).getCourseID();
        }else
            return courseSchema.retrieveCourse(course).getCourseID();
    }//retrieveCourseID

    public int retrieveBookID(Textbook textbook){
        if(textBookSchema.retrieveTextBook(textbook) == null) {
            textBookSchema.addTextBook(textbook);

            return textBookSchema.retrieveTextBook(textbook).getBookID();
        }else
            return textBookSchema.retrieveTextBook(textbook).getBookID();
    }//retrieveBookID

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }//onItemSelected

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }//onNothingSelected
}//class
