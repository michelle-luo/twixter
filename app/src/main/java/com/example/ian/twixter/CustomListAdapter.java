package com.example.ian.twixter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ian on 4/28/17.
 */

public class CustomListAdapter extends ArrayAdapter<Newsitem> implements Filterable {
    private ArrayList<Newsitem> mOriginalValues;     // Original Values
    private ArrayList<Newsitem> mDisplayedValues;    // Values to be displayed

    private static class ViewHolder {
        TextView usernameView;
        TextView feedView;
    }

    public CustomListAdapter (ArrayList<Newsitem> listData, Context context) {
        super(context, R.layout.list_row_layout, listData);
        this.mOriginalValues = listData;
        this.mDisplayedValues = listData;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Newsitem newsitem = getItem(position);  // get the item for this position

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_row_layout, parent, false);
            holder = new ViewHolder();
            holder.usernameView = (TextView) convertView.findViewById(R.id.FeedUsername);
            holder.feedView = (TextView) convertView.findViewById(R.id.FeedContent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.usernameView.setText(newsitem.getUsername());
        holder.feedView.setText(newsitem.getFeed());

        return convertView;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.e("my tag", "result.count = " + results.count);
                mDisplayedValues = (ArrayList<Newsitem>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Newsitem> FilteredArrList = new ArrayList<Newsitem>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Newsitem>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {
                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String username = mOriginalValues.get(i).getUsername();
                        String tweet = mOriginalValues.get(i).getFeed();
                        if (username.toLowerCase().contains(constraint.toString().toLowerCase()) || tweet.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            FilteredArrList.add(new Newsitem(mOriginalValues.get(i).getUsername(), mOriginalValues.get(i).getFeed()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}
