package de.eahjena.app.wi.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErgebnisDetailActivity extends AppCompatActivity {

    private RequestQueue torRequestQueue;
    private RecyclerView torRecyclerView;

    private List<SpielstandDetail> detailListe;

    String Endergebnis;
    String SpielstartRichtig;
    String TorMinute;
    String Torschütze;
    String AnzahlToreHeim;
    String AnzahlToreGast;
    String aktuellerSpielstand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnisdetail);

        getSupportActionBar().setTitle("Ergebnis im Detail");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));

        torRecyclerView = findViewById(R.id.recyclerViewTore);
        torRecyclerView.setHasFixedSize(true);
        torRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        torRequestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        detailListe = new ArrayList<>();

        //Füllen der Image + Textviews


        ImageView LogoHeimmannschaftErg = findViewById(R.id.LogoHeimmannschaftErg);
        ImageView LogoGastmannschaftErg = findViewById(R.id.LogoGastmannschaftErg);

        TextView HeimmannschaftErg = findViewById(R.id.HeimmannschaftErg);
        TextView GastmannschaftErg = findViewById(R.id.GastmannschaftErg);

        TextView EndergebnisErg = findViewById(R.id.EndergebnisErg);
        TextView ZwischenergebnisErg = findViewById(R.id.ZwischenergebnisErg);
        TextView SpielstartErg = findViewById(R.id.SpielstartErg);
        TextView StadionErg = findViewById(R.id.StadionErg);
        TextView ZuschauerErg = findViewById(R.id.ZuschauerErg);


        Bundle bundle = getIntent().getExtras();

        String HeimLogoErg = bundle.getString("LogoHeim");


        String GastLogoErg = bundle.getString("LogoGast");


        String HeimMannschaft = bundle.getString("Heimmannschaft");

        String GastMannschaft = bundle.getString("Gastmannschaft");


        String EndergebnisEr = bundle.getString("Endergebnis");


        String ZwischenergebnisEr = bundle.getString("Zwischenergebnis");


        String SpielstartEr = bundle.getString("Spielstart");


        String StadionEr = bundle.getString("Stadion");


        String ZuschauerEr = bundle.getString("Zuschauer");

        String MatchID = bundle.getString("matchID");




        Glide.with(this).load(HeimLogoErg).into(LogoHeimmannschaftErg);
        Glide.with(this).load(GastLogoErg).into(LogoGastmannschaftErg);

        HeimmannschaftErg.setText(HeimMannschaft);
        GastmannschaftErg.setText(GastMannschaft);

        EndergebnisErg.setText(EndergebnisEr);
        ZwischenergebnisErg.setText(ZwischenergebnisEr);

        SpielstartErg.setText(SpielstartEr);
        StadionErg.setText(StadionEr);
        ZuschauerErg.setText(ZuschauerEr);

        //Erstellen des Recyclerviews

        parseJSONSpielstand(MatchID);


    }
    private void parseJSONSpielstand(String MatchID) {


        String Link = "https://api.openligadb.de/getmatchdata/bl1";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Link, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //Abfrage des Spielstarts und Umwandlung in "richtiges" Format



                for (int i = 0; i < response.length(); i++) {


                    //if (i == 0) {

                        try {


                            JSONObject FirstObject = response.getJSONObject(i);


                            JSONArray Tore = FirstObject.getJSONArray("goals");

                            String MatchID2 = FirstObject.getString("matchID");

                            for (int y = 0; y < Tore.length(); y++) {

                                if (MatchID.equals(MatchID2)) {

                                    JSONObject TorObject = Tore.getJSONObject(y);


                                    TorMinute = TorObject.getString("matchMinute");
                                    Torschütze = TorObject.getString("goalGetterName");
                                    AnzahlToreHeim = TorObject.getString("scoreTeam1");
                                    AnzahlToreGast = TorObject.getString("scoreTeam2");
                                    aktuellerSpielstand = AnzahlToreHeim + ":" + AnzahlToreGast;


                                    SpielstandDetail SpielstandDetail = new SpielstandDetail(TorMinute, aktuellerSpielstand, Torschütze);
                                    detailListe.add(SpielstandDetail);

                                    SpielstandAdapter adapter1 = new SpielstandAdapter(ErgebnisDetailActivity.this, detailListe);

                                    torRecyclerView.setAdapter(adapter1);


                                }

                            }










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

        torRequestQueue.add(jsonArrayRequest);






    };

}
