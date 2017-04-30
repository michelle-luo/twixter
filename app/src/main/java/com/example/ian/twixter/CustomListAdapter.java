package com.example.ian.twixter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ian on 4/28/17.
 */

public class CustomListAdapter extends ArrayAdapter<Newsitem>{
    private ArrayList<Newsitem> listData;
    Context mContext;

    private static class ViewHolder {
        TextView usernameView;
        TextView feedView;
    }

    public CustomListAdapter (ArrayList<Newsitem> listData, Context context) {
        super(context, R.layout.list_row_layout, listData);
        this.listData = listData;
        this.mContext=context;
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
}
