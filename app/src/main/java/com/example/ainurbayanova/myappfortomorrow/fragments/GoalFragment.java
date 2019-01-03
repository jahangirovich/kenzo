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

import com.example.ainurbayanova.myappfortomorrow.Goal;
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
    goalAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goal, container, false);
        initWidgets();
        initClickListenerForFab();
        return view;
    }

    private void initClickListenerForFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater factory = LayoutInflater.from(getActivity());

                final View aler_view = factory.inflate(R.layout.alert_dialog, null);
                final View date_picker = factory.inflate(R.layout.date_picker, null);
                final View time_picker = factory.inflate(R.layout.time_picker, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                final TextView date = aler_view.findViewById(R.id.date);
                final TextView time = aler_view.findViewById(R.id.time);
                final EditText title=  aler_view.findViewById(R.id.title);
                final AlertDialog dateDialog = new AlertDialog.Builder(getActivity()).create();
                final AlertDialog timeDialog = new AlertDialog.Builder(getActivity()).create();
                String final_time = "";

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Button ok = date_picker.findViewById(R.id.ok);
                        final StringBuilder final_date = new StringBuilder();
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePicker datePicker = date_picker.findViewById(R.id.picker);
                                final_date.append(String.valueOf(datePicker.getYear()) + "/");
                                final_date.append(String.valueOf(datePicker.getMonth()) + "/");
                                final_date.append(String.valueOf(datePicker.getDayOfMonth()));
                                dateDialog.dismiss();
                                alertDialog.show();
                                date.setText(final_date);
                            }
                        });
                        dateDialog.setView(date_picker);
                        dateDialog.show();
                    }
                });
                time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Button ok = time_picker.findViewById(R.id.ok);
                        final StringBuilder final_date = new StringBuilder();
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TimePicker timePicker = time_picker.findViewById(R.id.time_picker);
                                final_date.append(String.valueOf(timePicker.getCurrentHour()) + ":");
                                final_date.append(String.valueOf(timePicker.getCurrentMinute()) + "");
                                timeDialog.dismiss();
                                alertDialog.show();
                                time.setText(final_date);
                            }
                        });
                        timeDialog.setView(time_picker);
                        timeDialog.show();
                    }
                });
                Button button = aler_view.findViewById(R.id.btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String titles = title.getText().toString();
                        String dates = date.getText().toString();
                        String times = time.getText().toString();
                        if(!titles.equals("") && !dates.equals("") && !times.equals("")){
                            goals.add(new Goal(titles,dates,times));
                            adapter.notifyDataSetChanged();
                            alertDialog.dismiss();
                        }
                        else{
                            Toast.makeText(getActivity(),"Please fill all conditions",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.setView(aler_view);
                alertDialog.show();
            }
        });
    }

    private void initWidgets() {
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new goalAdapter(getActivity(),goals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

}
