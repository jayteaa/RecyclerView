package de.eahjena.app.wi.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



public class BeispielAdapter extends RecyclerView.Adapter<BeispielAdapter.ExampleViewHolder>  {

    private Context context;
    private List<beispielitemspielergebnis> ergebnisListe;


    public BeispielAdapter(Context context , List<beispielitemspielergebnis> ergebnisse){

        this.context = context;
        ergebnisListe = ergebnisse;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beispielitemspielergebnis , parent , false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        beispielitemspielergebnis beispielitemspielergebnis = ergebnisListe.get(position);
        holder.Heimmannschaft.setText(beispielitemspielergebnis.getHeimmannschaft());
        holder.Gastmannschaft.setText(beispielitemspielergebnis.getGastmannschaft());
        holder.Endergebnis.setText(beispielitemspielergebnis.getEndergebnis());
        Glide.with(context).load(beispielitemspielergebnis.getLogoHeim()).into(holder.LogoHeimmannschaft);
        Glide.with(context).load(beispielitemspielergebnis.getLogoGast()).into(holder.LogoGastmannschaft);


      holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, ErgebnisDetailActivity.class);

                Bundle bundle = new Bundle();
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ergebnisListe.size();
    }




    public class ExampleViewHolder extends RecyclerView.ViewHolder{


        public TextView Heimmannschaft;
        public TextView Gastmannschaft;
        public TextView Endergebnis;
        public ImageView LogoHeimmannschaft;
        public ImageView LogoGastmannschaft;
        public TextView Spieltagsnummer;
        ConstraintLayout constraintLayout;



        public ExampleViewHolder(@NonNull View itemView) {

            super(itemView);

            Heimmannschaft = itemView.findViewById(R.id.Heimmannschaft);
            Gastmannschaft = itemView.findViewById(R.id.Gastmannschaft);
            Endergebnis = itemView.findViewById(R.id.Spielergebnis);
            LogoHeimmannschaft = itemView.findViewById(R.id.LogoHeimmannschaft);
            LogoGastmannschaft = itemView.findViewById(R.id.LogoGastmannschaft);
            Spieltagsnummer = itemView.findViewById(R.id.SpieltagsNummer);
            constraintLayout = itemView.findViewById(R.id.ergebnisDetailErg);
        }
    }
}

