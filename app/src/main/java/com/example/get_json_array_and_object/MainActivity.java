package com.example.get_json_array_and_object;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
       // JSONObject response=new JSONObject();
        String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int count = 0;
                        JSONObject meta= null;
                        try {
                            meta = response.getJSONObject("metadata");
                            count=(Integer)meta.getInt("count");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        textView.setText("Response: " + count);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("myapp", "something wrong");

                    }
                });

// Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest);
    }
}
