package com.example.fetchrewardsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fetchrewardsapplication.adapters.FetchListAdapter;
import com.example.fetchrewardsapplication.models.FetchItemModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<FetchItemModel> models = new ArrayList<>();
    FetchListAdapter arrayAdapter;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.fetch_list_view);
        arrayAdapter = (FetchListAdapter) new FetchListAdapter(
                this,
                R.layout.list_fetch_item,
                models);

        lv.setAdapter(arrayAdapter);


        queue = Volley.newRequestQueue(this);
        queue.add(getRequest("https://fetch-hiring.s3.amazonaws.com/hiring.json"));

    }
    private JsonArrayRequest getRequest(String uri){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
        (Request.Method.GET, uri, null, new Response.Listener<org.json.JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {
                try {
                    getFetchItems(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                return ;
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            }
        });
        return jsonArrayRequest;
    }

    private void readStream(InputStream in) {
    }

    private void getFetchItems(JSONArray jsonObject) throws JSONException {
        FetchItemModel item;
        for (int i=0; i<jsonObject.length(); i++) {
            int id = jsonObject.getJSONObject(i).getInt("id");
            int listID = jsonObject.getJSONObject(i).getInt("listId");
            String name = jsonObject.getJSONObject(i).getString("name");
            item = new FetchItemModel(id, listID, name);
            models.add(item);
        }

        arrayAdapter.notifyDataSetChanged();;
    }
}