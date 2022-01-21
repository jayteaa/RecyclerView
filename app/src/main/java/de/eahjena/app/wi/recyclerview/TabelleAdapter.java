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


public class TabelleAdapter extends RecyclerView.Adapter<TabelleAdapter.TabelleViewHolder> {


    private Context context;
    private List<ItemTabelle> tabellenInhalteList;


    public TabelleAdapter(Context context , List<ItemTabelle> tblInhalteListe){

        this.context = context;
        tabellenInhalteList = tblInhalteListe;


    }

    @NonNull
    @Override
    public TabelleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beispielitemtabelle , parent , false);
        return new TabelleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabelleViewHolder holder, int position) {




        ItemTabelle ItemTabelle = tabellenInhalteList.get(position);
        holder.Platzierung.setText(ItemTabelle.getPlatzierung().toString());
        Glide.with(context).load(ItemTabelle.getTeamLogo()).into(holder.TeamLogo);
        holder.Mannschaftsname.setText(ItemTabelle.getMannschaftsname());
        holder.SpielAnzahl.setText(ItemTabelle.getSpielAnzahl());
        holder.SiegAnzahl.setText(ItemTabelle.getSiegAnzahl());
        holder.UnentschiedenAnzahl.setText(ItemTabelle.getUnentschiedenAnzahl());
        holder.NiederlagenAnzahl.setText(ItemTabelle.getNiederlagenAnzahl());
        //holder.TorAnzahl.setText(ItemTabelle.getTorAnzahl());
        holder.TorDifferenz.setText(ItemTabelle.getTorDifferenz());
        holder.Punkte.setText(ItemTabelle.getPunkte());


    }

    @Override
    public int getItemCount() {
        return tabellenInhalteList.size();
    }



    public class TabelleViewHolder extends RecyclerView.ViewHolder{



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



        public TabelleViewHolder(@NonNull View itemView) {

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

