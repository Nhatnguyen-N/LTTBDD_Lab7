package com.example.sql_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Name> nameList;
    NameAdapter adt;
    Button btAdd, btDelete, btCancel;
    TextView tvName;
    DataBaseHandlerName DBName;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBName = new DataBaseHandlerName(this);

        listView = findViewById(R.id.list_view);
        btAdd = findViewById(R.id.btAdd);
        btDelete = findViewById(R.id.btDelete);
        btCancel = findViewById(R.id.btCancel);
        tvName = findViewById(R.id.input_name);

        nameList = new ArrayList<>();
        nameList = DBName.getNames();

        adt = new NameAdapter(this,nameList,R.layout.items_7_1);
        listView.setAdapter(adt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                Toast.makeText(MainActivity.this, index + "", Toast.LENGTH_SHORT).show();
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tvName.getText().toString();
                tvName.setText("");
                DBName.addName(new Name(name));
                update();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBName.deleteName(nameList.get(index));
                update();
            }
        });



    }
    private void update(){
        nameList.clear();
        List<Name> temp = DBName.getNames();
        for (Name n : temp){
            nameList.add(n);
        }
        adt.notifyDataSetChanged();
    }
}