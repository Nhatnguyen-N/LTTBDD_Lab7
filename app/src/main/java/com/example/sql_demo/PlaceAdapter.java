package com.example.sql_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {

    Context context;
    int idLayout;
    List<Place> placeList;

    public PlaceAdapter(Context context, int idLayout, List<Place> placeList) {
        this.context = context;
        this.idLayout = idLayout;
        this.placeList = placeList;
    }

    @Override
    public int getCount() {
        return placeList.size();
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

        TextView tvSTT= view.findViewById(R.id.tvSTT);
        TextView tvTenPlace = view.findViewById(R.id.tv_ten_edit);

        tvTenPlace.setText(placeList.get(i).getDiadiem());
        tvSTT.setText(placeList.get(i).getSTT());
        return view;
    }
}
