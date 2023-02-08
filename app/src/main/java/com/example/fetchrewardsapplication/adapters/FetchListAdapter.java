package com.example.fetchrewardsapplication.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fetchrewardsapplication.R;
import com.example.fetchrewardsapplication.models.FetchItemModel;

import java.util.List;

public class FetchListAdapter extends ArrayAdapter<FetchItemModel> {

    private int resourceLayout;
    private Context mContext;

    public FetchListAdapter(Context context, int resource, List<FetchItemModel> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        FetchItemModel p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.fetch_id);
            TextView tt2 = (TextView) v.findViewById(R.id.listId);
            TextView tt3 = (TextView) v.findViewById(R.id.name);
            if (tt1 != null) {
                tt1.setText(((Integer) p.getId()).toString());
            }
            if (tt2 != null) {
                tt2.setText(((Integer) p.getListId()).toString());
            }
            if (tt3 != null) {
                tt3.setText(p.getName());
            }
        }

        return v;
    }

}


