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


public class SpielstandAdapter extends RecyclerView.Adapter<SpielstandAdapter.SpielstandViewHolder>  {

    private Context context;
    private List<SpielstandDetail> spielstandListe;






    public SpielstandAdapter(Context context , List<SpielstandDetail> detailliert){

        this.context = context;
        spielstandListe = detailliert;


    }

    @NonNull
    @Override
    public SpielstandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdetailtore , parent , false);
        return new SpielstandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpielstandViewHolder holder, int position) {

        SpielstandDetail SpielstandDetail = spielstandListe.get(position);


        holder.Spielminute.setText(SpielstandDetail.getSpielminuteTor());
        holder.Torschütze.setText(SpielstandDetail.getTorschütze());
        holder.SpielstandUpdate.setText(SpielstandDetail.getSpielstandUpdate());



    }

    @Override
    public int getItemCount() {
        return spielstandListe.size();
    }




    public class SpielstandViewHolder extends RecyclerView.ViewHolder{


        public TextView Spielminute;
        public TextView Torschütze;
        public TextView SpielstandUpdate;


        RelativeLayout RelativeLayout;



        public SpielstandViewHolder(@NonNull View itemView) {

            super(itemView);

            Spielminute = itemView.findViewById(R.id.Spielminute);
            Torschütze = itemView.findViewById(R.id.Torschütze);
            SpielstandUpdate = itemView.findViewById(R.id.SpielstandUpdate);

            RelativeLayout = itemView.findViewById(R.id.ItemSpielstand);

        }
    }
}

