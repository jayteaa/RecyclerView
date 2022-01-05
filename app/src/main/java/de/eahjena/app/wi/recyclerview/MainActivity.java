package de.eahjena.app.wi.recyclerview;

import android.os.Bundle;
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

        parseJSON();



    }

    private void parseJSON() {


        String url = "https://api.openligadb.de/getmatchdata/bl1/2021/15";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject FirstObject = response.getJSONObject(i);

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

}






















 /*       String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonArrayRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)

                        try {

                            JSONObject Objekt1 = response.getJSONObject(i);
                            String MatchID = Objekt1.getString("matchID").toString();
                            System.out.println(MatchID);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}*/


        //JSONObject Objekt1 = response.getJSONObject(i);
        //String MatchID = Objekt1.getString("matchID").toString();
        //                  System.out.println(MatchID);





        /*String url = "https://api.openligadb.de/getmatchdata/bl1/2021/15";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int i = 0; i < response.length(); i++) {


                        JSONObject MatchObject = response.getJSONObject(i);
                        JSONObject TeamObject = MatchObject.getJSONObject("team1");
                        String team1Name = TeamObject.getString("teamName");

                        JSONObject TeamObject2 = MatchObject.getJSONObject("team2");
                        String team2Name = TeamObject2.getString("teamName");

                        beispielListe.add(new beispielitemspielergebnis(R.drawable.fortuna_duesseldorf, R.drawable.fc_augsburg, team1Name, team2Name, "0-2"));
                    }


                    mExampleAdapter = new BeispielAdapter(this, beispielListe);
                    mRecyclerView.setAdapter(mExampleAdapter);

                    //String team1 = MatchObject.getString()
                    //String team1 = MatchObject.getString()
                    //String team1 = MatchObject.getString()
                    //       int logoHeimmannschaft =
                    //              int logoGastmannschaft
                    //wir brauchen:
                    // Name Heimmannschaft (team1, teamName),
                    // Name Gastmannschaft (team 2, teamName),
                    // Endergebnis (matchResults, pointsTeam1, pointsTeam2)
                    // Logo Heimmannschaft (team1, teamIconUrl),
                    // Logo Gastmannschaft (team 2, teamIconUrl),


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }





        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


    }*/
