package com.example.ainurbayanova.myappfortomorrow;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ainurbayanova.myappfortomorrow.Model.Item;
import com.example.ainurbayanova.myappfortomorrow.Model.ModelTask;

import java.util.ArrayList;
import java.util.List;

public class CurrentTasksAdapter extends RecyclerView.Adapter<CurrentTasksAdapter.ViewHolder> {

    List<Item> items = new ArrayList<>();
    private static final int TYPE_TASK = 0;
    private static final int TYPE_SEPARATOR = 1;

    public Item getItems(int position) {
        return items.get(position);
    }

    public void addItem(Item item){
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItem(int position,Item item){
        items.add(position,item);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;

        public ViewHolder(@NonNull View itemView,TextView title,TextView date) {
            super(itemView);
            this.date = date;
            this.title = title;
        }
    }

    @NonNull
    @Override
    public CurrentTasksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case TYPE_TASK:
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_of_current,viewGroup,false);
                TextView title = v.findViewById(R.id.title);
                TextView date = v.findViewById(R.id.date);
                return new ViewHolder(v,title,date);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull CurrentTasksAdapter.ViewHolder viewHolder, int i) {
        Item item = items.get(i);

        if (item.isTask()){
            viewHolder.itemView.setEnabled(true);
            ModelTask modelTask = (ModelTask) item;
            ViewHolder view = viewHolder;

            view.title.setText(modelTask.getTitle());
            if(modelTask.getDate() != 0){
                view.date.setText(modelTask.getDate() + "");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(getItems(position).isTask()){
            return TYPE_TASK;
        }
        else{
            return TYPE_SEPARATOR;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
