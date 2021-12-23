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

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private BeispielAdapter mExampleAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<beispielitemspielergebnis>beispielListe;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beispielListe= new ArrayList<>();


        //beispielListe.add(new beispielitemspielergebnis(R.drawable.borussia_dortmund, R.drawable.bayern_muenchen, "Borussia Dortmund", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.a_1_fc_nuernberg, R.drawable.tsg_hoffenheim, "1.FC Nürnberg", "TSG Hoffenheim", "3-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.bayer_leverkusen, R.drawable.hannover_96, "Bayer Leverkusen", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.eintracht_frankfurt, R.drawable.hertha_bsc, "Eintracht Frankfurt", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.bor_moenchengladbach, R.drawable.sc_freiburg, "Borussia Mönchengladbach", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.vfl_wolfsburg, R.drawable.werder_bremen, "VfL Wolfsburg", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.hamburger_sv, R.drawable.vfb_stuttgart, "Hamburger SV", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.fortuna_duesseldorf, R.drawable.a_1_fsv_mainz_05, "Fortuna Düsseldorf", "FC Bayern München", "0-1"));
        //beispielListe.add(new beispielitemspielergebnis(R.drawable.eintracht_braunschweig, R.drawable.fc_augsburg, "Eintracht Braunschweig", "Freiburg", "0-0"));

    mRecyclerView = findViewById(R.id.recyclerViewSpielergebnis);
    //wenn Größe des RecyclerViews sich nicht verändert -> verbessert Performance und mehr als 9 Items werden nicht benötigt
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(this);
    mAdapter = new BeispielAdapter(beispielListe);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);
    mRequestQueue = Volley.newRequestQueue(this);
    parseJSON();


    }

    private void parseJSON() {
        beispielListe= new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openligadb.de/getmatchdata/bl1/2021/15";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        setContentView(R.layout.activity_main);
                        beispielListe= new ArrayList<>();

                        JSONObject FirstObject = response.getJSONObject(i);
                        JSONObject jsonObject2 = FirstObject.getJSONObject("team1");
                        String Heimmannschaft = jsonObject2.getString("teamName");

                        System.out.println(Heimmannschaft);
                        beispielListe.add(new beispielitemspielergebnis(R.drawable.borussia_dortmund, R.drawable.bayern_muenchen, Heimmannschaft, "FC Bayern München", "0-1"));



                        //JSONObject SecondObject = response.getJSONObject(i);
                        //String TestMannschaft = FirstObject.getString("team1");












                        /*JSONObject jsonObject1 = response.getJSONObject(i);
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("team1");
                        String TestMannschaft = jsonObject2.getString("teamName");
                        JSONObject jsonObject3 = jsonObject1.getJSONObject("team2");
                        String TestGastMannschaft = jsonObject3.getString("teamName");
                        System.out.println(TestMannschaft);
                        //System.out.println(TestGastMannschaft);
                       /* JSONObject Objekt1 = response.getJSONObject(i);
                        String MatchID = Objekt1.getString("matchID").toString();
                        JSONObject Objekt2 = response.getJSONObject(i);
                        String TeamName = Objekt2.getString("team1").toString();
                        System.out.println(TeamName);
                        System.out.println(MatchID);*/








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
