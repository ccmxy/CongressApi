package com.example.epicodus.congressapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;



/**
 * Created by Guest on 11/3/15.
 */
public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Title: This is an errror!!!!!!")
                .setMessage("Message: Panic!!!!")
                .setPositiveButton("Ahhhhhh!", null);

        AlertDialog dialog = builder.create();
        return dialog;
    }

}
