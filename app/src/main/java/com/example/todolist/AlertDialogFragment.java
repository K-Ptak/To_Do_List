package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertDialogFragment extends AppCompatDialogFragment {

    AlertDialogListener listener;
    int id;

    public AlertDialogFragment(int id){
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialog_layout, null);

        builder.setView(view)
                .setTitle("Delete")
                .setMessage("Do you want to delete this item from the list?")
                .setCancelable(false)
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .setPositiveButton("Yes", (dialogInterface, i) -> listener.apply(id));

        return builder.create();
    }

    public static String TAG = "AlertDialog";

    public interface AlertDialogListener {
        void apply(int position);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AlertDialogListener) context;

    }

}