package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.TextBookDisplay;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.Tables.TextBookPostSchema;

public class UserPostList extends AppCompatActivity {

    TextBookPostSchema textBookPostSchema;
    TextView tvEmpty;
    ListView lvTextBookList;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvEmpty             = (TextView) findViewById(R.id.tvEmpty);
        textBookPostSchema  = new TextBookPostSchema(this);
        lvTextBookList      = (ListView) findViewById(R.id.lvUserPost);
        sessionManager      = new SessionManager(this);
        final Intent intent = new Intent(this, PostTextbook.class);

        lvTextBookList.setClickable(true);

        lvTextBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextBookDisplay textBookDisplay = (TextBookDisplay)parent.getAdapter().getItem(position);
                Bundle bundle                   = new Bundle();

                bundle.putString("postID", Integer.toString(textBookDisplay.getPostid()));
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
        Intent myIntent = new Intent(getApplicationContext(), Profile.class);
        startActivityForResult(myIntent, 0);

        return true;
    }//onOptionsItemSelected

    public void loadList(){
        ArrayList<TextBookDisplay> textBookList = textBookPostSchema.retrieveTextBookDisplayList(sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL));
        ListAdapter la                          = new TextBookDisplayAdapter(this, R.layout.adapter_textbook_display_list, textBookList);

        if(textBookList.size() <= 0)
            tvEmpty.setText("No Textbook Posts!");

        lvTextBookList.setAdapter(la);
    }//loadList

}//class
