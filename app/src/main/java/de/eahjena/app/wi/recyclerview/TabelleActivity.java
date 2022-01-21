package de.eahjena.app.wi.recyclerview;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TabelleActivity extends AppCompatActivity{

    private RecyclerView tblRecyclerView;
    private RequestQueue tblRequestQueue;

    private List<ItemSpielergebnis> ergebnisListe;
    private List<ItemTabelle> tabellenInhalteList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewtabelle);

        getSupportActionBar().setTitle("Tabelle 1. Bundesliga");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));


        tblRecyclerView = findViewById(R.id.recyclerViewTabelleTest);
        tblRecyclerView.setHasFixedSize(true);
        tblRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tblRequestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        ergebnisListe = new ArrayList<>();
        tabellenInhalteList = new ArrayList<>();

        parseJSONTabelle();


    }


    private void parseJSONTabelle() {


        String Link = "https://api.openligadb.de/getbltable/bl1/2021";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Link, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject Test = response.getJSONObject(i);

                        String teamName = Test.getString("teamName");
                        System.out.println(teamName);

                        String teamIconUrl = Test.getString("teamIconUrl");
                        System.out.println(teamIconUrl);

                        String points = Test.getString("points");
                        System.out.println(points);

                        String opponentGoals = Test.getString("opponentGoals");
                        System.out.println(opponentGoals);

                        String goals = Test.getString("goals");
                        System.out.println(goals);

                        String matches = Test.getString("matches");
                        System.out.println(matches);

                        String won = Test.getString("won");
                        System.out.println(won);

                        String lost = Test.getString("lost");
                        System.out.println(lost);

                        String draw = Test.getString("draw");
                        System.out.println(draw);

                        String goalDiff = Test.getString("goalDiff");
                        System.out.println(goalDiff);


                        //hier einfügen der ausgelesenen Strings in die Liste


                        ItemTabelle ItemTabelle = new ItemTabelle(i +1 , teamIconUrl,teamName,matches,won,draw,lost,goals,goalDiff,points);
                        tabellenInhalteList.add(ItemTabelle);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    TabelleAdapter adapter = new TabelleAdapter(TabelleActivity.this, tabellenInhalteList);

                    tblRecyclerView.setAdapter(adapter);


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        tblRequestQueue.add(jsonArrayRequest);

    }


}























