// Description
// Simple AlertBuilder with positvie and negative buttons
// Also implements call back listener in MainActivity


package com.example.jkozlevcar.lab3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

// this class represents the missile dialog
public class ResetDialogFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // set the Message

        builder.setMessage("Reset to defaults");

        // set the title
        builder.setTitle("Reset");

        // set the positive button
        // ("Fire", new DialogInterface.OnclickListener()
        builder.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // call the listener method in the main activity
                rListener.onDialogPositiveClick();
            }
        });

        // set the negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // User cancelled the dialog
                // call the listener method in the main activity
                rListener.onDialogNegativeClick();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface ResetDialogListener
    {
        // these method will be implemented in the MainActivity
        public void onDialogPositiveClick();
        public void onDialogNegativeClick();
    }

    // Use this instance of the interface to deliver action events
    private ResetDialogListener rListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
//    public void onAttach(Activity activity) // is deprecated
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the MissleDialogListener so we can send events to the host
            rListener = (ResetDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement MissleDialogListener");
        }
    }
}
