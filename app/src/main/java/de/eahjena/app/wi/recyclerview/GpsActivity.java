package de.eahjena.app.wi.recyclerview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GpsActivity extends AppCompatActivity {




    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        getSupportActionBar().setTitle("Aktueller Standort");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));

        //Variable zuordnen
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        //fused location initialisieren
        client = LocationServices.getFusedLocationProviderClient(this);

        //Erlaubnis prüfen

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //wenn Erlaubnis gegeben wurde


            //Methodenaufruf

            getCurrentLocation();
        } else {

            //Wenn Erlaubnis abgelehnt wurde
            //Erlaubnis erfragen
            ActivityCompat.requestPermissions(GpsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }


    private void getCurrentLocation() {

        //Initialisieren des Standortes

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {

                //When success

                if (location != null) {

                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //Initialisieren von lat und lng

                            LatLng latLng = new LatLng(location.getLatitude()
                                    , location.getLongitude());

                            //Markierungs Optionen erstellen
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("ich bin hier");


                            //heranzoomen an Karte
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                            //Markierung auf der Karte hinzufügen
                            googleMap.addMarker(options);


                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Wenn Erlaubnis gegeben
                //Rufe Methode auf
                getCurrentLocation();
            }
        }
    }
}


