package directedstudy.georgiacentral.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        loadList();
    }//onCreate

    public void loadList(){
        ArrayList<TextBookDisplay> textBookList = textBookPostSchema.retrieveTextBookDisplayList();
        ListAdapter la                          = new TextBookDisplayAdapter(this, R.layout.adapter_textbook_display_list, textBookList);

        lvTextBookList.setAdapter(la);
    }//loadList

}//class
