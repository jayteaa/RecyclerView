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



}
