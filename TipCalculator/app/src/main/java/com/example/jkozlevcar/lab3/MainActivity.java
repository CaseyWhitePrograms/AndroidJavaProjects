// Description:
// A simple tip calculator using radio buttons for the tip amount.


package com.example.jkozlevcar.lab3;

import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ResetDialogFragment.ResetDialogListener
{
    // declare Java references to objects
    private EditText etAmount;
    private EditText etTotal;
    private EditText etTip;
    private Spinner spSplit;
    private SeekBar sbTip;

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;

    //key string for default tip
    private final String DEFAULT_TIP = "default_tip";

    //Integer variable to hold tip
    private int defaultTip;
    private Context context = this;

    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //.setAction("Action", null).show();

                //Added for Lab 5: create the Intent
                Intent intent = new Intent(context, PreferenceActivity.class);
                //start the activity
                startActivity(intent);
            }

        });

        // create Java objects and connect to GUI resources
        etAmount = findViewById(R.id.etAmount);
        etTotal = findViewById(R.id.etTotal);
        etTip = findViewById(R.id.etTip);

        // set no focus on the total editTExt
        etTotal.setFocusable(false);
        etTip.setFocusable(false);


        // NOTE: onKeyListener doesn't work for soft keyboards.
        // a custom editText is required to get soft keyboards backspace key
        etAmount.setOnKeyListener(new View.OnKeyListener() {
            // View: The view the key has been dispatched to.
            // int: The code for the physical key that was pressed
            // KeyEvent: The KeyEvent object containing full information about the event.
            // Returns: True if the listener has consumed the event, false otherwise.
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                System.out.println("onKey called");
                calculateTip();
                // must be false so the event is passed to the editText
                return false;
            }
        });

        // called when the check is selected on the keyboard.
        etAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                System.out.println("onEditorAction called");
                return false;
            }
        });

        // added for Lab4
        spSplit = findViewById(R.id.spSplit);
        sbTip = findViewById(R.id.sbTip);

        // add listeners
        spSplit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // call calculateTip
                calculateTip();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sbTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // call calculateTip
                calculateTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //added for Lab5
        //set up preferences in private mode
        preferenceSettings = getSharedPreferences("lab5", PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();
        //get the data from the preferences with default if no preference
        defaultTip = preferenceSettings.getInt("DEFAULT_TIP", 10);
        //set the default tip in the seek bars
        sbTip.setProgress(defaultTip);

        //added for lab 6
        btnReset = findViewById(R.id.btnReset);
        //set listener for reset button
        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //create the dialog
                DialogFragment newFragment = new ResetDialogFragment();
                //show it
                newFragment.show(getSupportFragmentManager(),  "reset");
            }
        });
    }

    // method to calculate tip using if logic and radio button isChecked method
    private void calculateTip() {
        // declare local variables
        double amount = 0;
        double total = 0;
        double tip = 0;
        int split = 1;
        double tipPercent = 15;

        // get the split
        split = spSplit.getSelectedItemPosition() + 1;

        // get the tip percentage
        tipPercent = sbTip.getProgress();

        try {
            // get the amount
            amount = Double.parseDouble(etAmount.getText().toString());

            // calculate tip and amount
            tip = amount * tipPercent / 100;
            total = (amount + tip) / split;
        } catch (Exception e) {
            // do nothing
        }

        // display the total in the total editText
        etTotal.setText(String.format("$%.2f", total));

        // display tip in the tip editText
        etTip.setText(String.format("$%.2f (%2.0f%%)", tip, tipPercent));
    }

    // method to calculate tip using switch logic and radio button id
//    private void calculateTip1()
//    {
//        // declare local variables
//        double amount = 0;
//        double total = 0;
//        double tip = 0;
//
//        try
//        {
//            // get the amount
//            amount = Double.parseDouble(etAmount.getText().toString());
//
//            // which tip % is selected
//            switch(rgPercent.getCheckedRadioButtonId())
//            {
//                case R.id.rb10percent:
//                    tip = amount * 0.1;
//                    total = amount * 1.1;
//                    break;
//                case R.id.rb15percent:
//                    tip = amount * 0.15;
//                    total = amount * 1.15;
//                    break;
//                case R.id.rb20percent:
//                    tip = amount * 0.2;
//                    total = amount * 1.2;
//                    break;
//                case R.id.rb25percent:
//                    tip = amount * 0.25;
//                    total = amount * 1.25;
//                    break;
//                case R.id.rb30percent:
//                    tip = amount * 0.3;
//                    total = amount * 1.3;
//                    break;
//            }
//        }
//        catch(Exception e)
//        {
//            // do nothing
//        }
//
//        // display the total in the total editText
//        etTotal.setText(String.format("$%.2f", total));
//
//        // udpate 9/9/18
//        // display the tip in the tip editText
//        etTip.setText(String.format("$%.2f", tip));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        System.out.println("MainActivity onStart");

        // get the data from the preferences with default if no preference
        defaultTip = preferenceSettings.getInt("DEFAULT_TIP", 10);
        // set the default tip in the seek bar
        sbTip.setProgress(defaultTip);

    }


    @Override
    public void onDialogPositiveClick()
    {
        //set the editText to 0
        etAmount.setText("");
        etTotal.setText("");

        //display hint in the editTexts
        etAmount.setHint("Amount");
        etTotal.setHint("Total");

        //set the split to 1, remember 0 based index
        spSplit.setSelection(0);

        //set the progress to the preference default
        sbTip.setProgress(defaultTip);

        //display tip in the tip editText
        etTip.setText(String.format("$%.2f (%2.0f%%)", 0.0, (double)defaultTip));
    }

    @Override
    public void onDialogNegativeClick()
    {

    }
}
