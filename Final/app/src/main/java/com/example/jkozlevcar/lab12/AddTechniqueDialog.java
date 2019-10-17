// Project:		Java III Project
// Class Name:	AddContactBusinessDialog
// Date:        2/21/19
// Author:      White
// Description:
// Add a technique position to the database


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


public class AddTechniqueDialog extends DialogFragment
{
    // create a reference for the inflater
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
        builder.setTitle("Add Technique");

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

        // call method to generate test data
        generateTestData();

        // add a positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                System.out.println("Add dialog onClick called");


                // create a Technique position object from the user input
                try
                {
                    TechniquePosition tpObject = new TechniquePosition(
                                    etPosition.getText().toString(),
                                    etAttack.getText().toString(),
                                    etRank.getText().toString(),
                                    etHours.getText().toString(),
                                    etDate.getText().toString());

                    // debug
                    System.out.println(tpObject.toString());

                    // call method in mainActivity to add a technique position to the database
                    addDialogListener.addDialogPositiveClick(tpObject);
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

    // generates test data for the add dialog
    private void generateTestData()
    {

        etPosition.setText("Position");
        etAttack.setText("Attack");
        etRank.setText("Rank");
        etHours.setText("Hours Trained");
        etDate.setText("Date");
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface AddDialogListener
    {
        // these method will be implemented in the MainActivity
        public void addDialogPositiveClick(TechniquePosition tpObject);
        public void addDialogNegativeClick();
    }

    // Use this reference of the interface to deliver action events
    private AddDialogListener addDialogListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the AddDialogListener so we can send events to the host
            addDialogListener = (AddDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement AddDialogListener");
        }
    }
}
