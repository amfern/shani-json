package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         final TextView textView = (TextView) findViewById(R.id.textEx);
// ...

        RequestQueue queue = Volley.newRequestQueue(this);

// Instantiate the RequestQueue.
        final String url = "https://gist.githubusercontent.com/Peratryn/3772776/raw/cd036999613408a90d1681332712ad6ee1ca9889/achievement.json";

// Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = response.getJSONArray("criteria");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject criteria = jsonArray.getJSONObject(i);
                                String bookName = criteria.getString("description");
                                int number = criteria.getInt("id");
                                textView.append(bookName + "\n" + number);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
                Log.e("error", "error", error);

            }

        });
        queue.add(stringRequest);
    }
}