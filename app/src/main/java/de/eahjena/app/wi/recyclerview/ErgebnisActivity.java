package de.eahjena.app.wi.recyclerview;

import android.graphics.drawable.ColorDrawable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ErgebnisActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;

    private List<ItemSpielergebnis> ergebnisListe;
    private List<ItemDetail> detailListe;
    private List<ItemTabelle> tabellenInhalteList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewspielergebnis);

        getSupportActionBar().setTitle("Spielergebnisse 1. Bundesliga");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));

        mRecyclerView = findViewById(R.id.recyclerViewSpielergebnisTest);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRequestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        ergebnisListe = new ArrayList<>();
        tabellenInhalteList = new ArrayList<>();
        detailListe = new ArrayList<>();

        parseJSONErgebnisse();


    }

    private void parseJSONErgebnisse() {


        String url = "https://api.openligadb.de/getmatchdata/bl1/2021/19";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject FirstObject = response.getJSONObject(i);

                        String Spielstart = FirstObject.getString("matchDateTime");
                        System.out.println(Spielstart);

                        String Stadion = FirstObject.getString("location");
                        System.out.println(Stadion);

                        String Zuschauer = FirstObject.getString("numberOfViewers");
                        System.out.println(Zuschauer);

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

                                ItemSpielergebnis ItemSpielergebnis = new ItemSpielergebnis(Heimmannschaft, Gastmannschaft, Endergebnis, "",
                                                                            Stadion ,Zuschauer, Spielstart, LogoH, LogoG);

                                ergebnisListe.add(ItemSpielergebnis);

                            }







                        }







                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    ErgebnisAdapter adapter = new ErgebnisAdapter(ErgebnisActivity.this, ergebnisListe, detailListe);


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






};

}























