package com.example.sql_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    List<Place> placeList;
    PlaceAdapter adt;
    TextView tv;
    Button btSave, btCancel;
    int index =0;

    private static MainActivity2 instance;
    private static DataBaseHandlerPlace DBPlace;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        instance = this;

        DBPlace = new DataBaseHandlerPlace(this);
        DBPlace.deletePlace(2);
        DBPlace.deletePlace(3);
        DBPlace.deletePlace(4);

        listView = findViewById(R.id.listview_place);
        tv = findViewById(R.id.input_place);
        btSave = findViewById(R.id.btSave);

        placeList = new ArrayList<>();
        placeList = DBPlace.getPlaces();
        adt = new PlaceAdapter(this,R.layout.items_7_2,placeList);
        listView.setAdapter(adt);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Place place = new Place(tv.getText().toString());
                DBPlace.addPlace(place);
                tv.setText("");
                update();
            }
        });
    }
    public static MainActivity2 getInstance(){
        return instance;
    }
    public static DataBaseHandlerPlace getDB(){
        return DBPlace;
    }
    public void update(){
        placeList.clear();
        List<Place> temp = DBPlace.getPlaces();
        for(Place p : temp){
            placeList.add(p);
        }
        adt.notifyDataSetChanged();
    }
}
