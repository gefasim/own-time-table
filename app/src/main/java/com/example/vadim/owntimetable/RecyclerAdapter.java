package com.example.vadim.owntimetable;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vadim.owntimetable.Model.Lesson;

import java.util.List;


class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Lesson> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView mTextView2;

        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
            mTextView2 = (TextView) v.findViewById(R.id.tv_recycler_item2);
        }
    }

    RecyclerAdapter(List<Lesson> dataset) {
        mDataset = dataset;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getLesson_time());
        holder.mTextView2.setText(mDataset.get(position).getLesson_name());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}