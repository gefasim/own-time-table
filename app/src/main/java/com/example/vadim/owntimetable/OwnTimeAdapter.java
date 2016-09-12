package com.example.vadim.owntimetable;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by root on 9/4/16.
 */
public class OwnTimeAdapter extends RecyclerView.Adapter<OwnTimeAdapter.DataHolder> {

    private List<String> cardtext = new ArrayList<>();

    public static class DataHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView text;
        DataHolder(View itemView) {
            super(itemView);
            cardview = (CardView)itemView.findViewById(R.id.cv);
            text = (TextView)itemView.findViewById(R.id.cv_textview);

        }
    }

    public OwnTimeAdapter(List<String> cardtext){
        this.cardtext = cardtext;
    }

    @Override
    public int getItemCount() {
        return cardtext.size();
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        DataHolder pvh = new DataHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DataHolder personViewHolder, int i) {
        personViewHolder.text.setText(cardtext.get(i));

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
