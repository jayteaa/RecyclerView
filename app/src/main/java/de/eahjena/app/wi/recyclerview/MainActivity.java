package de.eahjena.app.wi.recyclerview;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;

    private RecyclerView tblRecyclerView;
    private RequestQueue tblRequestQueue;

    private List<ItemSpielergebnis> ergebnisListe;
    private List<ItemTabelle> tabellenInhalteList;




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























