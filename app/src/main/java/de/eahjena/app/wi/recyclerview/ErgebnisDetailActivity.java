package de.eahjena.app.wi.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ErgebnisDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnisdetail);


        ImageView LogoHeimmannschaftErg = findViewById(R.id.LogoHeimmannschaftErg);
        ImageView LogoGastmannschaftErg = findViewById(R.id.LogoGastmannschaftErg);

        TextView EndergebnisErg = findViewById(R.id.EndergebnisErg);
        TextView ZwischenergebnisErg = findViewById(R.id.ZwischenergebnisErg);
        TextView SpielstartErg = findViewById(R.id.SpielstartErg);
        TextView StadionErg = findViewById(R.id.StadionErg);
        TextView ZuschauerErg = findViewById(R.id.ZuschauerErg);


        Bundle bundle = getIntent().getExtras();

        String HeimLogoErg = bundle.getString("teamIconUrl");
        /*String GastLogoErg = bundle.getString("teamIconUrl");

        String EndergebnisEr = bundle.getString("overview");
        String ZwischenergebnisEr = bundle.getString("overview");
        String SpielstartEr = bundle.getString("overview");
        String StadionEr = bundle.getString("overview");
        String ZuschauerEr = bundle.getString("overview");
*/


        Glide.with(this).load(HeimLogoErg).into(LogoHeimmannschaftErg);
        /*Glide.with(this).load(GastLogoErg).into(LogoGastmannschaftErg);
        EndergebnisErg.setText(EndergebnisEr);
        ZwischenergebnisErg.setText(ZwischenergebnisEr);
        SpielstartErg.setText(SpielstartEr);
        StadionErg.setText(StadionEr);
        ZuschauerErg.setText(ZuschauerEr);*/

    }
}
