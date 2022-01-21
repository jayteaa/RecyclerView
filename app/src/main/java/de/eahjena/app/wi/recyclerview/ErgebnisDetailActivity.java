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

import java.util.ArrayList;
import java.util.List;

public class ErgebnisDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnisdetail);

        getSupportActionBar().setTitle("Ergebnis im Detail");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));


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
        System.out.println("HeimLogoErg" + HeimLogoErg);

        String GastLogoErg = bundle.getString("LogoGast");
        System.out.println("GastLogoErg" + GastLogoErg);

        String HeimMannschaft = bundle.getString("Heimmannschaft");

        String GastMannschaft = bundle.getString("Gastmannschaft");


        String EndergebnisEr = bundle.getString("Endergebnis");
        System.out.println("EndergebnisEr" + EndergebnisEr);

        String ZwischenergebnisEr = bundle.getString("Zwischenergebnis");
        System.out.println("ZwischenergebnisEr" + ZwischenergebnisEr);

        String SpielstartEr = bundle.getString("Spielstart");
        System.out.println("SpielstartEr" + SpielstartEr);

        String StadionEr = bundle.getString("Stadion");
        System.out.println("StadionEr" + StadionEr);

        String ZuschauerEr = bundle.getString("Zuschauer");
        System.out.println("ZuschauerEr" + ZuschauerEr);



        Glide.with(this).load(HeimLogoErg).into(LogoHeimmannschaftErg);
        Glide.with(this).load(GastLogoErg).into(LogoGastmannschaftErg);
        HeimmannschaftErg.setText(HeimMannschaft);
        GastmannschaftErg.setText(GastMannschaft);
        EndergebnisErg.setText(EndergebnisEr);
        ZwischenergebnisErg.setText(ZwischenergebnisEr);
        SpielstartErg.setText(SpielstartEr);
        StadionErg.setText(StadionEr);
        ZuschauerErg.setText(ZuschauerEr);




    }


    /*private void parseJSONDetails() {


        String url = "https://api.openligadb.de/getmatchdata/bl1/2021/19";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {



                        JSONObject FirstObject = response.getJSONObject(i);

                        String Spielstart = FirstObject.getString("matchDateTime");
                        System.out.print(Spielstart);

                        String Stadion = FirstObject.getString("location");

                        if (Stadion == null){

                            Stadion = "keine Angabe zum Stadion";
                        }

                        System.out.println(Stadion);

                        String Zuschauer = FirstObject.getString("location");

                        if (Zuschauer == null){

                             Zuschauer = "keine Angabe zu Zuschauern";
                        }
                        System.out.println(Zuschauer);



                        //
                        JSONObject jsonObject2 = FirstObject.getJSONObject("team1");
                        String Heimmannschaft = jsonObject2.getString("teamName");
                        String LogoHeim = jsonObject2.getString("teamIconUrl");
                        System.out.println(LogoHeim);



                        //
                        JSONObject jsonObject3 = FirstObject.getJSONObject("team2");
                        String Gastmannschaft = jsonObject3.getString("teamName");
                        String LogoGast = jsonObject3.getString("teamIconUrl");
                        System.out.println(LogoGast);




                        JSONArray matchResults = FirstObject.getJSONArray("matchResults");

                        for (int j = 0; j < matchResults.length(); j++) {


                            JSONObject SecondObject = matchResults.getJSONObject(j);


                            //If Clause, sodass nur das Endergebnis abgefragt wird, nicht das Zwischenergebnis


                                if (SecondObject.getInt("resultTypeID") == 1) {


                                    String ToreHeimZw = SecondObject.getString("pointsTeam1");
                                    String ToreGastZw = SecondObject.getString("pointsTeam2");
                                    String Zwischenergebnis = ToreHeimZw + ":" + ToreGastZw;


                                    System.out.println(Zwischenergebnis);

                                    ItemSpielergebnis ItemSpielergebnis = new ItemSpielergebnis(Heimmannschaft , Gastmannschaft , "", Zwischenergebnis, Stadion, Zuschauer, Spielstart,
                                            LogoHeim,  LogoGast);

                                    beispielListe.add(ItemSpielergebnis);



                                }


                            }



                        for (int k = 0; k < matchResults.length(); k++) {


                            JSONObject SecondObject = matchResults.getJSONObject(k);


                            //If Clause, sodass nur das Endergebnis abgefragt wird, nicht das Zwischenergebnis


                            if (SecondObject.getInt("resultTypeID") == 2) {


                                String ToreHeimZw = SecondObject.getString("pointsTeam1");
                                String ToreGastZw = SecondObject.getString("pointsTeam2");
                                String Endergebnis = ToreHeimZw + ":" + ToreGastZw;



                                System.out.println(Endergebnis);

                                ItemSpielergebnis ItemSpielergebnis = new ItemSpielergebnis(Heimmannschaft , Gastmannschaft , Endergebnis, "", Stadion, Zuschauer, Spielstart,
                                        LogoHeim,  LogoGast);

                                beispielListe.add(ItemSpielergebnis);

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

        mRequestQueue.add(jsonArrayRequest);

    }
*/
}
