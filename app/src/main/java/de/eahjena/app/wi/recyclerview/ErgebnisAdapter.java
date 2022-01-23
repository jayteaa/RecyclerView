package de.eahjena.app.wi.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



public class ErgebnisAdapter extends RecyclerView.Adapter<ErgebnisAdapter.ErgebnisViewHolder>  {

    private Context context;
    private List<ItemSpielergebnis> ergebnisListe;






    public ErgebnisAdapter(Context context , List<ItemSpielergebnis> ergebnisse, List<SpielstandDetail> details){

        this.context = context;
        ergebnisListe = ergebnisse;


    }

    @NonNull
    @Override
    public ErgebnisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beispielitemspielergebnis , parent , false);
        return new ErgebnisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ErgebnisViewHolder holder, int position) {

        ItemSpielergebnis ItemSpielergebnis = ergebnisListe.get(position);


        holder.Heimmannschaft.setText(ItemSpielergebnis.getHeimmannschaft());
        holder.Gastmannschaft.setText(ItemSpielergebnis.getGastmannschaft());
        holder.Endergebnis.setText(ItemSpielergebnis.getEndergebnis());
        Glide.with(context).load(ItemSpielergebnis.getLogoHeim()).into(holder.LogoHeimmannschaft);
        Glide.with(context).load(ItemSpielergebnis.getLogoGast()).into(holder.LogoGastmannschaft);


      holder.RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, ErgebnisDetailActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("Heimmannschaft", ItemSpielergebnis.getHeimmannschaft());
                bundle.putString("Gastmannschaft", ItemSpielergebnis.getGastmannschaft());

                bundle.putString("LogoHeim", ItemSpielergebnis.getLogoHeim());
                bundle.putString("LogoGast", ItemSpielergebnis.getLogoGast());

                bundle.putString("Endergebnis", ItemSpielergebnis.getEndergebnis());
                bundle.putString("Zwischenergebnis", ItemSpielergebnis.getZwischenergebnis());

                bundle.putString("Spielstart", "Spielstart: " + ItemSpielergebnis.getSpielstart());
                bundle.putString("Stadion", "Stadion: " + ItemSpielergebnis.getStadion());
                bundle.putString("Zuschauer", "Zuschauer: " + ItemSpielergebnis.getZuschauer());

                bundle.putString("matchID", ItemSpielergebnis.getMatchID());













                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return ergebnisListe.size();
    }




    public class ErgebnisViewHolder extends RecyclerView.ViewHolder{


        public TextView Heimmannschaft;
        public TextView Gastmannschaft;
        public TextView Endergebnis;
        public ImageView LogoHeimmannschaft;
        public ImageView LogoGastmannschaft;
        public TextView Spieltagsnummer;

        RelativeLayout RelativeLayout;



        public ErgebnisViewHolder(@NonNull View itemView) {

            super(itemView);

            Heimmannschaft = itemView.findViewById(R.id.Heimmannschaft);
            Gastmannschaft = itemView.findViewById(R.id.Gastmannschaft);
            Endergebnis = itemView.findViewById(R.id.Spielergebnis);
            LogoHeimmannschaft = itemView.findViewById(R.id.LogoHeimmannschaft);
            LogoGastmannschaft = itemView.findViewById(R.id.LogoGastmannschaft);
            Spieltagsnummer = itemView.findViewById(R.id.SpieltagsNummer);
            RelativeLayout = itemView.findViewById(R.id.Teest);

        }
    }
}

