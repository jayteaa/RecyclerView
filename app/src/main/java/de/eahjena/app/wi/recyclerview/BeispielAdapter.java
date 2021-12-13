package de.eahjena.app.wi.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BeispielAdapter extends RecyclerView.Adapter<BeispielAdapter.ExampleViewHolder>{

    private ArrayList<beispielitemspielergebnis>BbeispielListe;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView LogoHeimmannschaft;
        public ImageView LogoGastmannschaft;
        public TextView Heimmannschaft;
        public TextView Gastmannschaft;
        public TextView Endergebnis;


        public ExampleViewHolder(View itemView) {

            super(itemView);
            LogoHeimmannschaft = itemView.findViewById(R.id.LogoHeimmannschaft);
            LogoGastmannschaft = itemView.findViewById(R.id.LogoGastmannschaft);
            Heimmannschaft = itemView.findViewById(R.id.Heimmannschaft);
            Gastmannschaft = itemView.findViewById(R.id.Gastmannschaft);
            Endergebnis = itemView.findViewById(R.id.Spielergebnis);


        }
    }



    public BeispielAdapter(ArrayList<beispielitemspielergebnis>beispielListe) {
    BbeispielListe = beispielListe;


    }



    //Layout der Cardview weitergeben
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beispielitemspielergebnis, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }


    //Werte an die Image/Textview weitergeben
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
    beispielitemspielergebnis currentItem = BbeispielListe.get(position);

    holder.LogoHeimmannschaft.setImageResource(currentItem.getLogoHeimmannschaft());
    holder.LogoGastmannschaft.setImageResource(currentItem.getLogoGastmannschaft());
    holder.Heimmannschaft.setText(currentItem.getHeimmannschaft());
    holder.Gastmannschaft.setText(currentItem.getGastmannschaft());
    holder.Endergebnis.setText(currentItem.getEndergebnis());

    }

    @Override
    public int getItemCount() {


        return BbeispielListe.size();
    }
}
