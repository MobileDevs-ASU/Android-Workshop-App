package com.example.central.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Central on 1/31/17.
 */

public class OtherActivity extends AppCompatActivity {

    public final String TAG = "OtherActivity";

    TextView textView;
    ListView listView;

    ArrayAdapter arrayAdapter;
    ArrayList<String> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);

        Bundle bundle = getIntent().getExtras();
        String query = bundle.getString("query");
        // TODO: get json String from bundle here

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(query);

        listView = (ListView) findViewById(R.id.mylistview);
        // TODO: use json String to create eventList, then adapt eventList to listView
    }

    /**
     * This is going to parse the JSON string to get event information.
     * We are looking for three elements in every
     *
     * @param jsonString - the JSON data that will get returned from the server
     */
    private void createEventList(String jsonString) {
        eventList = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.has("_embedded")
                    && jsonObject.getJSONObject("_embedded").has("events")) {

                JSONObject embedded = jsonObject.getJSONObject("_embedded");
                JSONArray jsonArray = embedded.getJSONArray("events");

                // parse jsonArray to get the events, then put them in a object of their own
                for (int obj = 0; obj < jsonArray.length(); obj++) {

                    JSONObject eventObject = jsonArray.getJSONObject(obj);
                    String eventName = "";
                    String date = "";
                    String startTime = "";

                    try {
                        eventName = eventObject.getString("name");

                        JSONObject dates = eventObject.getJSONObject("dates");
                        JSONObject start = dates.getJSONObject("start");

                        date = start.getString("localDate");
                        startTime = start.getString("localTime");
                    } catch (JSONException e) {
                        Log.i(TAG, "Issue getting data from eventObject");
                    }

                    eventList.add(eventName +"\n" + date +"\n" + startTime);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
