// Project:		Jiujitsu Technique Java III
// Class Name:	UpdateContactBusinessDialog
// Date:        3/5/19
// Author:      White
// Description:
// Update a technique in the database


package com.example.jkozlevcar.lab12;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class UpdateTechniqueDialog extends DialogFragment
{
    // create a reference for the inflator
    private LayoutInflater inflater;

    // references for Java GUI objects
    private EditText etPosition;
    private EditText etAttack;
    private EditText etRank;
    private EditText etHours;
    private EditText etDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // set the title
        builder.setTitle("Update Technique");

        // Get the layout inflater object
        inflater = getActivity().getLayoutInflater();


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogLayout = inflater.inflate(R.layout.add_technique_dialog, null);
        builder.setView(dialogLayout);


        // create the Java objects and tie to the dialog GUI
        etPosition = dialogLayout.findViewById(R.id.etPosition);
        etAttack = dialogLayout.findViewById(R.id.etAttack);
        etRank = dialogLayout.findViewById(R.id.etRank);
        etHours = dialogLayout.findViewById(R.id.etHours);
        etDate = dialogLayout.findViewById(R.id.etDate);

        // set the name uneditable.
        etPosition.setEnabled(false);

        // copy data from arguments to edit texts
        // get the argument bundle
        Bundle args = getArguments();

        // get the string array with the details from the arg bundle
        String[] details = args.getStringArray(ItemDetailFragment.ARG_ITEM_ID);

        // copy the data from the detail array to the edit texts
        etPosition.setText(details[0]);
        etAttack.setText(details[1]);
        etRank.setText(details[2]);
        etHours.setText(details[3]);
        etDate.setText(details[4]);


        // add a positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                System.out.println("Update dialog onClick called");


                // create a Contact business object from the user input
                try
                {
                    TechniquePosition tpObject = new TechniquePosition(
                            etPosition.getText().toString(),
                            etAttack.getText().toString(),
                            etRank.getText().toString(),
                            etHours.getText().toString(),
                            etDate.getText().toString()
                    );

                    // debug
                    System.out.println(tpObject.toString());

                    // call method in mainActivity to add a technique position to the database
                    addListener.updateDialogPositiveClick(tpObject);
                }
                catch(Exception e)
                {
                    System.out.println("Catch: " + e.getMessage());
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface UpdateDialogListener
    {
        // these method will be implemented in the MainActivity
        public void updateDialogPositiveClick(TechniquePosition tpObject);
        public void updateDialogNegativeClick();
    }

    // Use this reference of the interface to deliver action events
    private UpdateDialogListener addListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the AddDialogListener so we can send events to the host
            addListener = (UpdateDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement AddDialogListener");
        }
    }
}
