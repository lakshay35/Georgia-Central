package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.TextBookDisplay;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.TextBookPostSchema;

public class TextbookSearch extends AppCompatActivity {

    TextBookPostSchema textBookPostSchema;
    ListView lvTextBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textBookPostSchema  = new TextBookPostSchema(this);
        lvTextBookList      = (ListView) findViewById(R.id.lvTextBookList);
        final Intent intent = new Intent(this, TextbookProfile.class);

        lvTextBookList.setClickable(true);

        lvTextBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextBookDisplay textBookDisplay = (TextBookDisplay)parent.getAdapter().getItem(position);
                Bundle bundle                   = new Bundle();

                bundle.putString("bookTitle", textBookDisplay.getBookTitle());
                bundle.putString("author", textBookDisplay.getAuthor());
                bundle.putString("courseNumber", textBookDisplay.getCourseNumber());
                bundle.putString("condition", textBookDisplay.getCondition());
                bundle.putString("price", String.valueOf(textBookDisplay.getPrice()));
                bundle.putString("name", textBookDisplay.getName());
                bundle.putString("email", textBookDisplay.getEmail());
                bundle.putString("phoneNumber", textBookDisplay.getPhoneNumber());
                bundle.putString("postDate", textBookDisplay.getPostDate());

                intent.putExtras(bundle);
                startActivity(intent);
            }//onItemClick
        });

        loadList();
    }//onCreate

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Homepage.class);
        startActivityForResult(myIntent, 0);

        return true;
    }//onOptionsItemSelected

    public void loadList(){
        ArrayList<TextBookDisplay> textBookList = textBookPostSchema.retrieveTextBookDisplayList();
        ListAdapter la                          = new TextBookDisplayAdapter(this, R.layout.adapter_textbook_display_list, textBookList);

        lvTextBookList.setAdapter(la);
    }//loadList

}//class
