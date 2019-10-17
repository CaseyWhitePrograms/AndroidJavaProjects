// Project:		JavaIII Project
// Class Name:	PreferenceDialog
// Date:        3/5/2019
// Author:      White
// Description
// Preference dialog for listing in master in ascending or descending order.
// Also implement call back listener in TechniqueMainActivity
// Dialog is called from menu in TechniqueMainActivity


package com.example.jkozlevcar.lab12;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


// this class represents the missle dialog
public class PreferenceDialog extends DialogFragment
{
    // references needed for shared preferences
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // set up preferences with name and in private mode
        preferenceSettings = getActivity().getSharedPreferences("project", PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();

        String pref = preferenceSettings.getString("DEFAULT_ORDER", "ASC");
        System.out.println("Default_Order: " + pref);

        // instead of a message, use a multi choice item
        String[] items = {"Ascending", "Descending"};
        int checkedItem;
        if(pref.equals("DESC"))
        {
            checkedItem = 1;
        }
        else
        {
            checkedItem = 0;
        }

        // single choice
        // 2nd arg -1 if no items checked
        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                // set the preference
                if(which == 0)
                {
                    preferenceEditor.putString("DEFAULT_ORDER", "ASC");
                }
                else if(which == 1)
                {
                    preferenceEditor.putString("DEFAULT_ORDER", "DESC");
                }
                preferenceEditor.apply();
            }
        });


        // set the title
        builder.setTitle("Preferences");

        // set the positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // call the listener method in the main activity
                pListener.preferenceDialogPositiveClick();
            }
        });

        // set the negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // User cancelled the dialog
                // call the listener method in the main activity
                pListener.preferenceDialogNegativeClick();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface PreferenceDialogListener
    {
        // these method will be implemented in the MainActivity
        public void preferenceDialogPositiveClick();
        public void preferenceDialogNegativeClick();
    }

    // Use this instance of the interface to deliver action events
    private PreferenceDialogListener pListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
//    public void onAttach(Activity activity) // is deprecated
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the DeleteDialogListener so we can send events to the host
            pListener = (PreferenceDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement PreferenceDialogListener");
        }
    }
}
