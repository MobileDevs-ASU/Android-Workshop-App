package com.example.central.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Sends a search query to the TicketMaster API, returns the JSON
 * result in the form of a String
 */

public class APIRequest extends AsyncTask<String, Integer, String> {

    // API values (API will only grab values from Arizona, to reduce information overload)
    private static final String TM_ROOT_URL = "https://app.ticketmaster.com/discovery/v2/events.json?";
    private static final String STATE_CODE = "&stateCode=AZ";
    private static String apiKey;

    private OtherActivity resultClass;
    private String searchQuery;

    private ProgressDialog dialog;

    public APIRequest(OtherActivity resultClass, String searchQuery) {
        this.resultClass = resultClass;
        this.searchQuery = searchQuery.trim();
    }

    @Override
    protected void onPreExecute() {
        Context context = resultClass.getContext();
        apiKey = context.getResources().getString(R.string.api_key);

        dialog = new ProgressDialog(context);
        dialog.setMessage("Searching Events");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false); // this was deprecated as of SDK 23

        dialog.show();
    }

    /**
     * @param params - we do not use this
     *
     * @return jsonObject - holds search results from TicketMaster API
     * @return null if results were not returned
     */
    @Override
    protected String doInBackground(String... params) {

        // check if the app has access to the internet
        if (ContextCompat.checkSelfPermission(
                resultClass.getContext(), android.Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        String resultString = null;

        String encodedSearch;
        String urlString;
        URL url;

        HttpURLConnection connection = null;
        InputStreamReader stream;
        BufferedReader buffer;

        try {

            // format the search query to be used in a URL
            encodedSearch = URLEncoder.encode(searchQuery, "UTF-8");

            urlString = TM_ROOT_URL +"apikey="+ apiKey +"&keyword="+ encodedSearch + STATE_CODE;
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // confirm that the connection returned a response code of 200 (OK)
            // TicketMaster API returns response code 401 when the key is invalid
            if (connection.getResponseCode() == 200) {

                // convert connection results to String
                stream = new InputStreamReader(connection.getInputStream());
                buffer = new BufferedReader(stream);

                resultString = "";
                String line;
                while ((line = buffer.readLine()) != null) {
                    resultString += line;
                }

            } else if (connection.getResponseCode() == 401) {
                dialog.setMessage("Invalid API key");
            } else {
                dialog.setMessage("Error Getting Data From Server");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    // disconnect error
                }
            }
        }

        return resultString;
    }

    @Override
    protected void onPostExecute(String json) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        resultClass.onFinish(searchQuery, json);
    }

}
