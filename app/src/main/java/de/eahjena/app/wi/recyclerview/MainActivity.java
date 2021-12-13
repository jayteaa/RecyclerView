package de.eahjena.app.wi.recyclerview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private ArrayList<beispielitemspielergebnis>BbeispielListe;
    //private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<beispielitemspielergebnis>beispielListe = new ArrayList<>();
        beispielListe.add(new beispielitemspielergebnis(R.drawable.borussia_dortmund, R.drawable.bayern_muenchen, "Borussia Dortmund", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.a_1_fc_nuernberg, R.drawable.tsg_hoffenheim, "1.FC Nürnberg", "TSG Hoffenheim", "3-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.bayer_leverkusen, R.drawable.hannover_96, "Bayer Leverkusen", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.eintracht_frankfurt, R.drawable.hertha_bsc, "Eintracht Frankfurt", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.bor_moenchengladbach, R.drawable.sc_freiburg, "Borussia Mönchengladbach", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.vfl_wolfsburg, R.drawable.werder_bremen, "VfL Wolfsburg", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.hamburger_sv, R.drawable.vfb_stuttgart, "Hamburger SV", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.fortuna_duesseldorf, R.drawable.a_1_fsv_mainz_05, "Fortuna Düsseldorf", "FC Bayern München", "0-1"));
        beispielListe.add(new beispielitemspielergebnis(R.drawable.eintracht_braunschweig, R.drawable.fc_augsburg, "Eintracht Braunschweig", "Freiburg", "0-0"));

    mRecyclerView = findViewById(R.id.recyclerViewSpielergebnis);
    //wenn Größe des RecyclerViews sich nicht verändert -> verbessert Performance und mehr als 9 Items werden nicht benötigt
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(this);
    mAdapter = new BeispielAdapter(beispielListe);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);

    }
}