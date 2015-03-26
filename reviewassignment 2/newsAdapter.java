package java2.devaunteledee.com.reviewassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devaunteledee on 3/25/15.
 */
public class newsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NYTimes> mObjects;

    private static final long ID_CONSTANT = 123456789;


    public newsAdapter(Context mContext, ArrayList<NYTimes> mObjects) {
        mObjects = new ArrayList<>();
        this.mContext = mContext;
        this.mObjects = mObjects;
        Log.i("MOBJECT","Mobject" +mObjects );
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public NYTimes getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listlayout, parent, false);

        }

        NYTimes item = getItem(position);
        TextView textView1 = (TextView)convertView.findViewById(R.id.newsTitle);
        TextView textView2 = (TextView)convertView.findViewById(R.id.newsAuthor);
        TextView textView3 = (TextView)convertView.findViewById(R.id.newsPublishDate);

        textView1.setText(item.articleName);
        textView2.setText(item.authorName);
        textView3.setText(item.published_date);

        return convertView;
    }


}
