package directedstudy.georgiacentral.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_textbook);

        etBookTitle                     = (EditText) findViewById(R.id.etBookTitle);
        etAuthor                        = (EditText) findViewById(R.id.etAuthor);
        etCourseNumber                  = (EditText) findViewById(R.id.etCourseNumber);
        etPrice                         = (EditText) findViewById(R.id.etPrice);
        spCondition                     = (Spinner) findViewById(R.id.spCondition);
        conditionSchema                 = new ConditionSchema(this);
        textBookSchema                  = new TextBookSchema(this);
        courseSchema                    = new CourseSchema(this);
        textBookPostSchema              = new TextBookPostSchema(this);
        sessionManager                  = new SessionManager(this);
        userSchema                      = new UserSchema(this);

        spCondition.setOnItemSelectedListener(this);
        loadSpinnerData();
    }//onCreate

    public void onClickSubmit(View view){
        Textbook textbook               = new Textbook(etBookTitle.getText().toString(), etAuthor.getText().toString());
        Course course                   = new Course(etCourseNumber.getText().toString());
        int textBookID                  = retrieveBookID(textbook);
        int courseID                    = retrieveCourseID(course);
        String condition                = spCondition.getSelectedItem().toString();
        int conditionID                 = conditionSchema.retrieveCondition(condition).getConditionID();
        String userName                 = sessionManager.getUserDetails().get("name");
        int userID                      = userSchema.retrieveUser(userName).getUserID();
        Calendar date                   = Calendar.getInstance();
        String postDate                 = new SimpleDateFormat("yyyyMMdd").format(date);

        date.add(Calendar.MONTH,1);

        String expireDate               = new SimpleDateFormat("yyyyMMdd").format(date);
        TextBookPost textBookPost       = new TextBookPost(userID, textBookID, courseID, conditionID, etPrice.getText().toString(), expireDate, postDate);
        boolean isPosted                = textBookPostSchema.addTextBookPost(textBookPost);
    }//onClickSubmit

    public void onClickCancel(View view){

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
