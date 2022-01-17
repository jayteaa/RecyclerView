package de.eahjena.app.wi.recyclerview;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;

    private RecyclerView tblRecyclerView;
    private RequestQueue tblRequestQueue;

    private List<beispielitemspielergebnis> ergebnisListe;
    private List<beispielitemtabelle> tabellenInhalteList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Fußball App");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));

        Button btnTabelle = findViewById(R.id.btn_tabelle);
        Button btnErgebnisse = findViewById(R.id.btn_ergebnisse);
        Button btnGps = findViewById(R.id.btn_gps);

        btnTabelle.setOnClickListener(this);
        btnErgebnisse.setOnClickListener(this);
        btnGps.setOnClickListener(this);


    }

    private void parseJSONErgebnisse() {


        String url = "https://api.openligadb.de/getmatchdata/bl1";


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


                        //hier einfügen der ausgelesenen Strings in die Liste


                        beispielitemtabelle beispielitemtabelle = new beispielitemtabelle (i +1 , teamIconUrl,teamName,matches,won,draw,lost,goals,goalDiff,points);
                        tabellenInhalteList.add(beispielitemtabelle);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    BeispielAdapterTabelle adapter = new BeispielAdapterTabelle(MainActivity.this, tabellenInhalteList);

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

    @Override
    public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btn_tabelle:


                    openTabelleActivity();

                    break;

                case R.id.btn_ergebnisse:


                    openErgebnisActivity();

                    break;

                case R.id.btn_gps:


                    openGpsActivity();

                    break;


            }

    }

    public void openTabelleActivity(){

        Intent intent = new Intent(this, TabelleActivity.class);
        startActivity(intent);

    }

    public void openErgebnisActivity(){

        Intent intent = new Intent (this, ErgebnisActivity.class);
        startActivity(intent);
    }

    public void openGpsActivity(){

        Intent intent = new Intent (this, GpsActivity.class);
        startActivity(intent);
    }
}























