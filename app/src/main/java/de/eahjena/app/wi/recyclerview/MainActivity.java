package de.eahjena.app.wi.recyclerview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private List<beispielitemspielergebnis> ergebnisListe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerViewSpielergebnis);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRequestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        ergebnisListe = new ArrayList<>();

        parseJSONErgebnisse();
        parseJSONTabelle();



    }

    private void parseJSONErgebnisse() {


        String url = "https://api.openligadb.de/getmatchdata/bl1/2021/17";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject FirstObject = response.getJSONObject(i);

                        JSONObject jsonObject1 = FirstObject.getJSONObject("group");
                        String SpieltagsNummer = jsonObject1.getString("groupName");
                        System.out.println(SpieltagsNummer);

                        TextView Spieltagsnr = findViewById(R.id.SpieltagsNummer);
                        Spieltagsnr.setText(SpieltagsNummer);  

                        //
                        JSONObject jsonObject2 = FirstObject.getJSONObject("team1");
                        String Heimmannschaft = jsonObject2.getString("teamName");
                        String LogoH = jsonObject2.getString("teamIconUrl");



                        //
                        JSONObject jsonObject3 = FirstObject.getJSONObject("team2");
                        String Gastmannschaft = jsonObject3.getString("teamName");
                        String LogoG = jsonObject3.getString("teamIconUrl");




                        JSONArray matchResults = FirstObject.getJSONArray("matchResults");

                        for (int j = 0; j < matchResults.length(); j++) {


                            JSONObject SecondObject = matchResults.getJSONObject(j);

                            //If Clause, sodass nur das Endergebnis abgefragt wird, nicht das Zwischenergebnis
                            if (SecondObject.getInt("resultTypeID") == 2){
                                String ToreHeim = SecondObject.getString("pointsTeam1");
                                String ToreGast = SecondObject.getString("pointsTeam2");
                                String Endergebnis = ToreHeim + ":" + ToreGast;

                                System.out.println(ToreHeim);
                                System.out.println(ToreGast);
                                System.out.println(Endergebnis);

                                beispielitemspielergebnis beispielitemspielergebnis = new beispielitemspielergebnis(Heimmannschaft, Gastmannschaft, Endergebnis, LogoH, LogoG);
                                ergebnisListe.add(beispielitemspielergebnis);

                            }
                        }







                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    BeispielAdapter adapter = new BeispielAdapter(MainActivity.this, ergebnisListe);

                    mRecyclerView.setAdapter(adapter);


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(jsonArrayRequest);

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




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(jsonArrayRequest);

    }
}























