package directedstudy.georgiacentral.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.TextBookDisplay;
import directedstudy.georgiacentral.R;

class TextBookDisplayAdapter extends ArrayAdapter<TextBookDisplay> {

    private ArrayList<TextBookDisplay> textBookDisplayList;
    private Context context;

    public TextBookDisplayAdapter(Context context, int id, ArrayList<TextBookDisplay> textBookDisplayList) {
        super(context, id, textBookDisplayList);

        this.context                    = context;
        this.textBookDisplayList        = textBookDisplayList;
    }//TextBookDisplayAdapter

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi   = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView         = vi.inflate(R.layout.adapter_textbook_display_list, null);

        TextBookDisplay textBookDisplayItem        = textBookDisplayList.get(position);
        TextView tvBookTitle                       = (TextView) convertView.findViewById(R.id.tvBookTItle);
        //TextView tvAuthor                          = (TextView) convertView.findViewById(R.id.tvAuthor);
        //TextView tvCourseNumber                    = (TextView) convertView.findViewById(R.id.tvCourseNumber);
        //TextView tvCondition                       = (TextView) convertView.findViewById(R.id.tvCondition);
        //TextView tvPrice                           = (TextView) convertView.findViewById(R.id.tvPrice);
        //TextView tvPostDate                        = (TextView) convertView.findViewById(R.id.tvPostDate);
        //TextView tvName                            = (TextView) convertView.findViewById(R.id.tvName);
        //TextView tvPhoneNumber                     = (TextView) convertView.findViewById(R.id.tvPhoneNumber);
        //TextView tvEmail                           = (TextView) convertView.findViewById(R.id.tvEmail);

        tvBookTitle.setText(textBookDisplayItem.getBookTitle());
        //tvAuthor.setText(textBookDisplayItem.getAuthor());
        //tvCourseNumber.setText(textBookDisplayItem.getCourseNumber());
        //tvCondition.setText(textBookDisplayItem.getCondition());
        //tvPrice.setText(Float.toString(textBookDisplayItem.getPrice()));
        //tvPostDate.setText(textBookDisplayItem.getPostDate());
        //tvName.setText(textBookDisplayItem.getName());
        //tvPhoneNumber.setText(textBookDisplayItem.getPhoneNumber());
        //tvEmail.setText(textBookDisplayItem.getEmail());

        return convertView;
    }//getView

}//class
