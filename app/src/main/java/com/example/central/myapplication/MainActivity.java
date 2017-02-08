package com.example.central.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * From Android's documentation:
 *
 * An activity is a single, focused thing that the user can do. Almost all activities interact
 * with the user, so the Activity class takes care of creating a window for you in which you
 * can place your UI with setContentView(View).
 *
 * Here, we are using AppCompatActivity to create our activity. AppCompatActivity is
 * extended from the Activity class, which means it retains the same functionality of
 * the Activity class, but implements a default ActionBar.
 */
public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remember to call the super method first
        super.onCreate(savedInstanceState);

        // specify the layout that this Activity will display
        setContentView(R.layout.activity_main);

        /**
         * The layout activity_main currently holds 3 views. Here, we are casing these views
         * as objects in order to programmatically manipulate them.
         *
         * findViewById() - this takes in an 'int ID' of a specified view. We get these
         *                  view ID's from 'R.id', where 'R' stands for resources and
         *                  '.id' stands for the ID subsection of those resources.
         *
         * Unfortunately, findViewById() only returns a generic View object, so it cannot
         * be immediately assigned to something like textView. As you can see, textView is
         * of the Class TextView, which is a subclass of View. Therefore, we will need to
         * case what findViewById() returns with (TextView) like so:
         */
        textView = (TextView) findViewById(R.id.mytextview);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        /**
         * setOnClickListener() will provide 'button' with an object OnClickListener.
         * Here, we are passing setOnClickListener() a new instance of OnClickListener
         * and overriding the onClick method as well.
         */
        final MainActivity mainActivity = this;

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String input = editText.getText().toString();

                // TODO: create APIRequest object, then execute it
                APIRequest request = new APIRequest(mainActivity, input);
                request.execute();
            }
        });
    }

    protected Context getContext() {
        return getApplicationContext();
    }

    /**
     * This method will be called by our APIRequest when it has finished
     *
     * @param searchQuery - the text that was entered into the editText, we
     *                      pass this to OtherActivity with Intent
     * @param json - the JSON data that will get returned from the server
     *             - WARNING: json will be null if APIRequest couldn't connect
     */
    protected void onFinish(String searchQuery, String json) {


        if (json != null) {
            Intent intent = new Intent(MainActivity.this, OtherActivity.class);
            intent.putExtra("query", searchQuery);

            OtherActivity.json = json;

            startActivity(intent);
        }
    }
}
