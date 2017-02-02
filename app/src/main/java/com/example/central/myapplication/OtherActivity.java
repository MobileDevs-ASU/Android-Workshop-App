package com.example.central.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Central on 1/31/17.
 */

public class OtherActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);

        Bundle bundle = getIntent().getExtras();
        String input = bundle.getString("editText");

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(input);

        listView = (ListView) findViewById(R.id.mylistview);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.listitem_layout, arrayList);

        listView.setAdapter(arrayAdapter);

    }
}
