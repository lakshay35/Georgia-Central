package directedstudy.georgiacentral.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.TextBookDisplay;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Tables.TextBookPostSchema;

import static directedstudy.georgiacentral.R.color.colorAccent;

class TextBookDisplayAdapter extends ArrayAdapter<TextBookDisplay> implements Filterable {

    private ArrayList<TextBookDisplay> textBookDisplayList;
    TextBookPostSchema textBookPostSchema;
    private Context context;

    public TextBookDisplayAdapter(Context context, int id, ArrayList<TextBookDisplay> textBookDisplayList) {
        super(context, id, textBookDisplayList);

        this.context                    = context;
        this.textBookDisplayList        = textBookDisplayList;
    }//TextBookDisplayAdapter

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi                           = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView                                 = vi.inflate(R.layout.adapter_textbook_display_list, null);
        TextBookDisplay textBookDisplayItem         = textBookDisplayList.get(position);
        TextView tvBookTitle                        = (TextView) convertView.findViewById(R.id.tvBookTitle);
        TextView tvName                             = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvAuthor                           = (TextView) convertView.findViewById(R.id.tvAuthor);
        TextView tvCourseNumber                     = (TextView) convertView.findViewById(R.id.tvCourseNumber);
        TextView tvCondition                        = (TextView) convertView.findViewById(R.id.tvCondition);
        TextView tvPrice                            = (TextView) convertView.findViewById(R.id.tvPrice);
        String price                                = String.format("%.02f", textBookDisplayItem.getPrice());

        tvBookTitle.setText(textBookDisplayItem.getBookTitle());
        tvAuthor.setText("Author(s): " + textBookDisplayItem.getAuthor());
        tvName.setText("Posted by: " + textBookDisplayItem.getName());
        tvCourseNumber.setText("Course(s): " + textBookDisplayItem.getCourseNumber());
        tvCondition.setText("Condition: " + textBookDisplayItem.getCondition());
        tvPrice.setText("$" + price);

        if(position % 2 == 0) {
            convertView.setBackgroundColor(colorAccent);
        }
        return convertView;
    }//getView

}//class
