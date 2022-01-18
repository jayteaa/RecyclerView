package de.eahjena.app.wi.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ErgebnisDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnisdetail);

        getSupportActionBar().setTitle("Ergebnis im Detail");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkred)));





        ImageView LogoHeimmannschaftErg = findViewById(R.id.LogoHeimmannschaftErg);
        ImageView LogoGastmannschaftErg = findViewById(R.id.LogoGastmannschaftErg);

        TextView EndergebnisErg = findViewById(R.id.EndergebnisErg);
        TextView ZwischenergebnisErg = findViewById(R.id.ZwischenergebnisErg);
        TextView SpielstartErg = findViewById(R.id.SpielstartErg);
        TextView StadionErg = findViewById(R.id.StadionErg);
        TextView ZuschauerErg = findViewById(R.id.ZuschauerErg);


        Bundle bundle = getIntent().getExtras();

        String HeimLogoErg = bundle.getString("teamIconUrl");
        System.out.println("HeimLogoErg" + HeimLogoErg);

        String GastLogoErg = bundle.getString("teamIconUrl");
        System.out.println("GastLogoErg" + GastLogoErg);

        String EndergebnisEr = bundle.getString("pointsTeam1");
        System.out.println("EndergebnisEr" + EndergebnisEr);

        String ZwischenergebnisEr = bundle.getString("pointsTeam2");
        System.out.println("ZwischenergebnisEr" + ZwischenergebnisEr);

        String SpielstartEr = bundle.getString("matchDateTime");
        System.out.println("SpielstartEr" + SpielstartEr);

        String StadionEr = bundle.getString("location");
        System.out.println("StadionEr" + StadionEr);

        String ZuschauerEr = bundle.getString("numberOfViewers");
        System.out.println("ZuschauerEr" + ZuschauerEr);



        Glide.with(this).load(HeimLogoErg).into(LogoHeimmannschaftErg);
        Glide.with(this).load(GastLogoErg).into(LogoGastmannschaftErg);
        EndergebnisErg.setText(EndergebnisEr);
        ZwischenergebnisErg.setText(ZwischenergebnisEr);
        SpielstartErg.setText(SpielstartEr);
        StadionErg.setText(StadionEr);
        ZuschauerErg.setText(ZuschauerEr);

    }
}
