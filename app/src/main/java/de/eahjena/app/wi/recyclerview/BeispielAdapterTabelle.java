package de.eahjena.app.wi.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class BeispielAdapterTabelle extends RecyclerView.Adapter<BeispielAdapterTabelle.ExampleViewHolder> {


    private Context context;
    private List<beispielitemtabelle> tabellenInhalteList;


    public BeispielAdapterTabelle(Context context , List<beispielitemtabelle> tblInhalteListe){

        this.context = context;
        tabellenInhalteList = tblInhalteListe;


    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beispielitemtabelle , parent , false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {




        beispielitemtabelle beispielitemtabelle = tabellenInhalteList.get(position);
        holder.Platzierung.setText(beispielitemtabelle.getPlatzierung().toString());
        Glide.with(context).load(beispielitemtabelle.getTeamLogo()).into(holder.TeamLogo);
        holder.Mannschaftsname.setText(beispielitemtabelle.getMannschaftsname());
        holder.SpielAnzahl.setText(beispielitemtabelle.getSpielAnzahl());
        holder.SiegAnzahl.setText(beispielitemtabelle.getSiegAnzahl());
        holder.UnentschiedenAnzahl.setText(beispielitemtabelle.getUnentschiedenAnzahl());
        holder.NiederlagenAnzahl.setText(beispielitemtabelle.getNiederlagenAnzahl());
        //holder.TorAnzahl.setText(beispielitemtabelle.getTorAnzahl());
        holder.TorDifferenz.setText(beispielitemtabelle.getTorDifferenz());
        holder.Punkte.setText(beispielitemtabelle.getPunkte());


    }

    @Override
    public int getItemCount() {
        return tabellenInhalteList.size();
    }



    public class ExampleViewHolder extends RecyclerView.ViewHolder{



        public TextView Platzierung;
        public ImageView TeamLogo;
        public TextView Mannschaftsname;
        public TextView SpielAnzahl;
        public TextView SiegAnzahl;
        public TextView UnentschiedenAnzahl;
        public TextView NiederlagenAnzahl;
        public TextView TorAnzahl;
        public TextView TorDifferenz;
        public TextView Punkte;



        public ExampleViewHolder(@NonNull View itemView) {

            super(itemView);

            Platzierung = itemView.findViewById(R.id.Platz);
            TeamLogo = itemView.findViewById(R.id.LogoHeimmannschaftTbl);
            Mannschaftsname = itemView.findViewById(R.id.Mannschaftsname);
            SpielAnzahl = itemView.findViewById(R.id.SpielAnzahl);
            SiegAnzahl = itemView.findViewById(R.id.SiegAnzahl);
            UnentschiedenAnzahl = itemView.findViewById(R.id.UnentschiedenAnzahl);
            NiederlagenAnzahl = itemView.findViewById(R.id.NiederlagenAnzahl);
            //TorAnzahl = itemView.findViewById(R.id.GesamtTore);
            TorDifferenz = itemView.findViewById(R.id.TorDifferenz);
            Punkte = itemView.findViewById(R.id.Punkte);

        }
    }
}

