package com.hechuang.labeego.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hechuang.labeego.R;


/**
 */

public class SearchTypeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private String[] type;
    private Context mContext;

    public SearchTypeAdapter(String[] type, Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.type = type;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return type.length;
    }

    @Override
    public Object getItem(int position) {
        return type[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchTypeViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_searchtype, null);
            holder = new SearchTypeViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.searchtype_name);
            convertView.setTag(holder);
        } else {
            holder = (SearchTypeViewHolder) convertView.getTag();
        }
        holder.name.setText(type[position]);
        return convertView;
    }

    private static class SearchTypeViewHolder {
        TextView name;
    }
}
