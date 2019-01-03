package com.example.ainurbayanova.myappfortomorrow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class goalAdapter extends RecyclerView.Adapter<goalAdapter.ViewHolder> {
    ArrayList<Goal> goals = new ArrayList<>();
    Context context;
    public goalAdapter(Context context,ArrayList<Goal> goals){
        this.context = context;
        this.goals = goals;
    }
    @NonNull
    @Override
    public goalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goal_design, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull goalAdapter.ViewHolder viewHolder, int i) {
        viewHolder.time.setText(goals.get(i).getTime());
        viewHolder.title.setText(goals.get(i).getTitle());
        viewHolder.date.setText(goals.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView time;
        public ViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
        }

    }
}
