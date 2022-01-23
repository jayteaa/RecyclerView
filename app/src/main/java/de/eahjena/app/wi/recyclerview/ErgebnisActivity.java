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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ErgebnisActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;


    private RecyclerView torRecyclerView;



    private List<ItemSpielergebnis> ergebnisListe;
    private List<SpielstandDetail> detailListe;


    String Endergebnis;
    String SpielstartRichtig;
    String TorMinute;
    String Torschütze;
    String AnzahlToreHeim;
    String AnzahlToreGast;
    String aktuellerSpielstand;
    String MatchID;
    String LogoH;
    String LogoG;




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
        detailListe = new ArrayList<>();

        parseJSONErgebnisse();


    }

    private void parseJSONErgebnisse() {


        String url = "https://api.openligadb.de/getmatchdata/bl1";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //Abfrage des Spielstarts und Umwandlung in "richtiges" Format

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject FirstObject = response.getJSONObject(i);

                        String Spielstart = FirstObject.getString("matchDateTime");
                        MatchID = FirstObject.getString("matchID");


                        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'hh:mm:ss");

                        try {

                            Date d = sdf.parse(Spielstart);
                        //Wandelt das Datum um in normales Format TT.MM.JJJJ SS.mm -> aufpassen mit m und M - Monate und Minuten
                        sdf.applyPattern("dd.MM.yyyy  HH:mm");
                        SpielstartRichtig = sdf.format(d);

                        }

                        catch (Exception e) {

                        }

                        //Abfrage des Stadionnamens
                        String Stadion = FirstObject.getString("location");

                        //Abfrage der Zuschaueranzahl
                        String Zuschauer = FirstObject.getString("numberOfViewers");

                        //Abfragen und einfügen der Spieltagsnummer in TextView
                        JSONObject jsonObject1 = FirstObject.getJSONObject("group");
                        String SpieltagsNummer = jsonObject1.getString("groupName");
                        TextView Spieltagsnr = findViewById(R.id.SpieltagsNummer);
                        Spieltagsnr.setText(SpieltagsNummer);  

                        //Abfrage der Heimmannschaft
                        JSONObject jsonObject2 = FirstObject.getJSONObject("team1");
                        String Heimmannschaft = jsonObject2.getString("teamName");

                        LogoH = jsonObject2.getString("teamIconUrl");



                        String ProblemLinkUnion =  "https://upload.wikimedia.org/wikipedia/commons/4/44/1._FC_Union_Berlin_Logo.svg";
                        String neuerLinkUnion = "https://de.wikipedia.org/wiki/1._FC_Union_Berlin#/media/Datei:1._FC_Union_Berlin_Logo.svg";








                        //if (LogoG == ProblemLinkUnion)
//
                       // { LogoG = neuerLinkUnion;}


                        //Abfrage der Gastmannschaft
                        JSONObject jsonObject3 = FirstObject.getJSONObject("team2");
                        String Gastmannschaft = jsonObject3.getString("teamName");


                        LogoG = jsonObject3.getString("teamIconUrl");




                        JSONArray Tore = FirstObject.getJSONArray("goals");

                        for (int y = 0; y < Tore.length(); y++) {


                            JSONObject TorObject = Tore.getJSONObject(y);

                            TorMinute = TorObject.getString("matchMinute");
                            Torschütze = TorObject.getString("goalGetterName");
                            AnzahlToreHeim = TorObject.getString("scoreTeam1");
                            AnzahlToreGast = TorObject.getString("scoreTeam2");
                            aktuellerSpielstand = AnzahlToreHeim + ":" + AnzahlToreGast;

                        }


                        //JSON Array abrufen über For-Schleife

                        JSONArray matchResults = FirstObject.getJSONArray("matchResults");

                        for (int j = 0; j < matchResults.length(); j++) {


                            //Abruf der Objekte innerhalb des Arrays
                            JSONObject SecondObject = matchResults.getJSONObject(j);

                            //Kondition, um nicht zwei Werte für eine Variable zu generieren

                            if (SecondObject.getInt("resultTypeID") == 2) {

                                String ToreHeimEnd = SecondObject.getString("pointsTeam1");
                                String ToreGastEnd = SecondObject.getString("pointsTeam2");
                                Endergebnis = ToreHeimEnd + ":" + ToreGastEnd;



                            }

                                else if (SecondObject.getInt("resultTypeID") == 1 ){


                                    String ToreHeimZwischen = SecondObject.getString("pointsTeam1");
                                    String ToreGastZwischen = SecondObject.getString("pointsTeam2");
                                    String Zwischenergebnis = "(" + ToreHeimZwischen + ":" + ToreGastZwischen + ")";







                                    ItemSpielergebnis ItemSpielergebnis = new ItemSpielergebnis(Heimmannschaft, Gastmannschaft, Endergebnis, Zwischenergebnis,
                                            Stadion, Zuschauer, SpielstartRichtig, LogoH, LogoG, MatchID);

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























