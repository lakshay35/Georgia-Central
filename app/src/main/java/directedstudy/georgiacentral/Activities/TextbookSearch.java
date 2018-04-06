package directedstudy.georgiacentral.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import directedstudy.georgiacentral.Objects.TextBookDisplay;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.Tables.TextBookPostSchema;

public class TextbookSearch extends AppCompatActivity {

    TextBookPostSchema textBookPostSchema;
    TextView tvEmpty;
    ListView lvTextBookList;
    ArrayAdapter<TextBookDisplay> la;
    ArrayList<TextBookDisplay> textBookList;
    ArrayList<TextBookDisplay> result;
    Spinner spOrder;
    TextView tvSort;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager      = new SessionManager(getApplicationContext());
        tvEmpty             = (TextView) findViewById(R.id.tvEmpty);
        tvSort              = (TextView) findViewById(R.id.tvSort);
        textBookPostSchema  = new TextBookPostSchema(this);
        lvTextBookList      = (ListView) findViewById(R.id.lvTextBookList);
        spOrder             = (Spinner)findViewById(R.id.spOrder);
        final Intent intent = new Intent(this, TextbookProfile.class);

        lvTextBookList.setTextFilterEnabled(true);
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

        tvSort.setText("Sort By:");
        loadSpinner();
        loadList();

        spOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final String sort = spOrder.getSelectedItem().toString();

                if(result != null)
                    textBookList = result;

                Collections.sort(textBookList, new Comparator<TextBookDisplay>(){
                    public int compare(TextBookDisplay obj1, TextBookDisplay obj2) {
                        if(sort.equals("Title"))
                            return obj1.getBookTitle().compareToIgnoreCase(obj2.getBookTitle());
                        else if(sort.equals("Author"))
                            return obj1.getAuthor().compareToIgnoreCase(obj2.getAuthor());
                        else if(sort.equals("Course"))
                            return obj1.getCourseNumber().compareToIgnoreCase(obj2.getCourseNumber());
                        else if(sort.equals("Price (Lowest)")) {
                            if(obj1.getPrice() > obj2.getPrice())
                                return 1;
                            else
                                return -1;
                        }else if(sort.equals("Price (Highest)")){
                            if(obj1.getPrice() > obj2.getPrice())
                                return -1;
                            else
                                return 1;
                        }else if(sort.equals("Condition")){
                            return obj1.getCondition().compareToIgnoreCase(obj2.getCondition());
                        }else
                            return obj1.getBookTitle().compareToIgnoreCase(obj2.getBookTitle());
                    }//compare
                });

                la  = new TextBookDisplayAdapter(getApplicationContext(), R.layout.adapter_textbook_display_list, textBookList);

                lvTextBookList.setAdapter(la);
            }//onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }//onNothingSelected
        });
    }//onCreate

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Homepage.class);
        startActivityForResult(myIntent, 0);

        return true;
    }//onOptionsItemSelected

    public void loadList(){
        textBookList                            = textBookPostSchema.retrieveTextBookDisplayList(sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL), sessionManager.getUserDetails().get(SessionManager.KEY_NAME));
        la                                      = new TextBookDisplayAdapter(this, R.layout.adapter_textbook_display_list, textBookList);

        if(textBookList.size() <= 0)
            tvEmpty.setText("No Textbook Posts!");

        lvTextBookList.setAdapter(la);
    }//loadList

    public void loadSpinner(){
        List<String> list                   = new ArrayList<String>();
        list.add("Title");
        list.add("Author");
        list.add("Course");
        list.add("Price (Lowest)");
        list.add("Price (Highest)");
        list.add("Condition");
        ArrayAdapter<String> dataAdapter    = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrder.setAdapter(dataAdapter);
    }//loadSpinner

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator   = getMenuInflater();
        inflator.inflate(R.menu.search_menu, menu);
        MenuItem item           = menu.findItem(R.id.menuSearch);
        SearchView searchView   = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }//onQueryTextSubmit

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                result  = new ArrayList<TextBookDisplay>();

                for(TextBookDisplay t : textBookList){
                    String title    = t.getBookTitle().toLowerCase();
                    String author   = t.getAuthor().toLowerCase();
                    String course   = t.getCourseNumber().toLowerCase();
                    String name     = t.getName().toLowerCase();

                    if(title.contains(newText) || author.contains(newText) || course.contains(newText) || name.contains(newText)){
                        result.add(t);
                    }//if
                }//for

                la                                      = new TextBookDisplayAdapter(getApplicationContext().getApplicationContext(), R.layout.adapter_textbook_display_list, result);
                lvTextBookList.setAdapter(la);

                return true;
            }//onQueryTextChange
        });

        return super.onCreateOptionsMenu(menu);
    }//onCreateOptionsMenu

}//class
