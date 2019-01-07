package com.example.ainurbayanova.myappfortomorrow.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ainurbayanova.myappfortomorrow.Model.ModelTask;
import com.example.ainurbayanova.myappfortomorrow.R;
import com.example.ainurbayanova.myappfortomorrow.Utils;

import java.util.Calendar;

public class AddingTaskDialogFragment extends DialogFragment {

    public AddingTaskInterface taskInterface;

    public interface AddingTaskInterface{
        void onTaskAdded(ModelTask modelTask);
        void onTaskAddingCancel();
    }
    AlertDialog.Builder builder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            taskInterface = (AddingTaskInterface) activity;
        }
        catch (ClassCastException e){
            throw  new ClassCastException(activity.toString() + " must implement AddingTask");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View container = layoutInflater.inflate(R.layout.alert_dialog,null);

        final TextView tilDate = container.findViewById(R.id.date);
        final EditText editText = container.findViewById(R.id.title);

        final ModelTask modelTask = new ModelTask();

        TextView tilTime = container.findViewById(R.id.time);

        builder.setView(container);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                modelTask.setDate(12);
                modelTask.setTitle(editText.getText().toString());
                taskInterface.onTaskAdded(modelTask);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                taskInterface.onTaskAddingCancel();
                dialog.dismiss();
            }
        });

        showIt();
        return super.onCreateDialog(savedInstanceState);
    }
    public void showIt(){
        builder.show();
    }
}
