// Project:         Jiujitsu Technique Project Java III
// Class:           ItemDetailActivity
// Author:          White
// Date:            3/5/19
// Description:     Modify the master/detail template to replace DummyCntent with a Cursor
//                  from the SQLite database business table

package com.example.jkozlevcar.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link TechniqueMainActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity
    implements UpdateTechniqueDialog.UpdateDialogListener
{

    // Method name:     onCreate
    // Arguments:       Bundle savedInstanceState - represents previous state
    // description:     creates the activity
    // Calling methods:
    // Methods called:
    // Returns:
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // create the FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // create argument bundle
                Bundle arguments = new Bundle();

                // get String[] details from intent and put it in the fragment arguments.
                arguments.putStringArray(ItemDetailFragment.ARG_ITEM_ID,
                        getIntent().getStringArrayExtra(ItemDetailFragment.ARG_ITEM_ID));

                // create the dialog
                DialogFragment newFragment = new UpdateTechniqueDialog();

                // add the args bundle to the dialog fragment
                newFragment.setArguments(arguments);

                // get the activity from the view
                ItemDetailActivity activity = (ItemDetailActivity)view.getContext();

                // show it
                newFragment.show(activity.getSupportFragmentManager(), "update");
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null)
        {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            // Final revision
            // get String[] details from intent and put it in the fragment arguments.
            arguments.putStringArray(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringArrayExtra(ItemDetailFragment.ARG_ITEM_ID));

            // create the fragment
            ItemDetailFragment fragment = new ItemDetailFragment();

            // set the arguments in the fragment
            fragment.setArguments(arguments);

            // start the fragment
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, TechniqueMainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // called by UpdateTechniqueTrained
    @Override
    public void updateDialogPositiveClick(TechniquePosition techniqueTrained)
    {
        // get the dbh
        TechniqueDatabaseHandler dbh = new TechniqueDatabaseHandler(this);

        // update the database
        dbh.updateTechnique(techniqueTrained);

        // go back to itemListActivity
        navigateUpTo(new Intent(this, TechniqueMainActivity.class));
    }

    @Override
    public void updateDialogNegativeClick()
    {

    }
}
