package com.example.sql_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends BaseAdapter {

    Context context;
    List<Name> listName;
    int idLayout;

    public NameAdapter(Context context, List<Name> listName, int idLayout) {
        this.context = context;
        this.listName = listName;
        this.idLayout = idLayout;
    }

    @Override
    public int getCount() {
        return listName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }

        TextView tvName= view.findViewById(R.id.tvName);

        tvName.setText(listName.get(i).getName());

        return view;
    }
}
