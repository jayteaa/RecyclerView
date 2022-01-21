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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;



public class ErgebnisActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;

    private List<ItemSpielergebnis> ergebnisListe;
    private List<ItemDetail> detailListe;
    private List<ItemTabelle> tabellenInhalteList;

    String Endergebnis;
    String SpielstartRichtig;




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


                        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'hh:mm:ss");

                        try {

                            Date d = sdf.parse(Spielstart);
                        //Wandelt das Datum um in normales Format TT.MM.JJJJ SS.mm -> aufpassen mit m und M - Monate und Minuten
                        sdf.applyPattern("dd.MM.yyyy  HH:mm");
                        SpielstartRichtig = sdf.format(d);

                        }

                        catch (Exception e) {

                        }

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

                            if (SecondObject.getInt("resultTypeID") == 2) {

                                String ToreHeimEnd = SecondObject.getString("pointsTeam1");
                                String ToreGastEnd = SecondObject.getString("pointsTeam2");
                                Endergebnis = ToreHeimEnd + ":" + ToreGastEnd;



                            }

                                else if (SecondObject.getInt("resultTypeID") == 1 ){


                                    String ToreHeimZwischen = SecondObject.getString("pointsTeam1");
                                    String ToreGastZwischen = SecondObject.getString("pointsTeam2");
                                    String Zwischenergebnis = "(" + ToreHeimZwischen + ":" + ToreGastZwischen + ")";



                                    System.out.println(Zwischenergebnis);



                                    ItemSpielergebnis ItemSpielergebnis = new ItemSpielergebnis(Heimmannschaft, Gastmannschaft, Endergebnis, Zwischenergebnis,
                                            Stadion, Zuschauer, SpielstartRichtig, LogoH, LogoG);

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























