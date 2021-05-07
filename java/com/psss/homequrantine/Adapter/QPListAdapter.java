package com.psss.homequrantine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.psss.homequrantine.R;

import java.util.ArrayList;
import java.util.List;

public class QPListAdapter extends BaseAdapter {

    Context context;
    List list = new ArrayList();
    TextView name;

    public QPListAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.hq_ppl_list_model,parent,false);

        name = convertView.findViewById(R.id.tv_name);
        name.setText(list.get(position).toString());

        return convertView;
    }
}
