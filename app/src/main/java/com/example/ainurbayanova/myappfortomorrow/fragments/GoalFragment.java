package com.example.ainurbayanova.myappfortomorrow.fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ainurbayanova.myappfortomorrow.CurrentTasksAdapter;
import com.example.ainurbayanova.myappfortomorrow.Goal;
import com.example.ainurbayanova.myappfortomorrow.Model.ModelTask;
import com.example.ainurbayanova.myappfortomorrow.R;
import com.example.ainurbayanova.myappfortomorrow.goalAdapter;

import java.util.ArrayList;

import static com.example.ainurbayanova.myappfortomorrow.MainActivity.fab;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {

    View view;
    public GoalFragment() {

    }
    ArrayList<Goal> goals = new ArrayList<>();
    CurrentTasksAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goal, container, false);
        initWidgets();

        return view;
    }

    private void initWidgets() {
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new CurrentTasksAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
    public void addTask(ModelTask newTask){
        int position = -1;
        for (int i = 0;i < adapter.getItemCount();i++){
            if(adapter.getItems(i).isTask()){
                ModelTask task = (ModelTask) adapter.getItems(i);
                if(newTask.getDate() < task.getDate()){
                    position = i;
                    break;
                }
            }
        }
        if(position != -1){
            adapter.addItem(position,newTask);
        }
        else{
            adapter.addItem(newTask);
        }
        Toast.makeText(getActivity(),"Inseted",Toast.LENGTH_SHORT).show();
    }
}
