package directedstudy.georgiacentral.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import directedstudy.georgiacentral.Objects.Condition;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.ConditionSchema;

public class PostTextbook extends AppCompatActivity implements OnItemSelectedListener {

    EditText etBookTitle;
    EditText etAuthor;
    EditText etCourseNumber;
    EditText etPrice;
    Spinner spCondition;
    ConditionSchema conditionSchema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_textbook);

        etBookTitle     = (EditText) findViewById(R.id.etBookTitle);
        etAuthor        = (EditText) findViewById(R.id.etAuthor);
        etCourseNumber  = (EditText) findViewById(R.id.etCourseNumber);
        etPrice         = (EditText) findViewById(R.id.etPrice);
        spCondition     = (Spinner) findViewById(R.id.spCondition);
        conditionSchema = new ConditionSchema(this);

        spCondition.setOnItemSelectedListener(this);
        loadSpinnerData();
    }//onCreate

    public void onClickSubmit(View view){

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }//onItemSelected

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }//onNothingSelected
}//class
