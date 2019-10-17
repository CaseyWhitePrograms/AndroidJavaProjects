// Project:         Jiujitsu Technique Java III
// Class:           ItemDetailFragment
// Author:          White
// Date:            2/21/19


// Method name:
// Arguments:
// description:
// Calling methods:
// Methods called:
// Returns:

package com.example.jkozlevcar.lab12;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jkozlevcar.lab12.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link TechniqueMainActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    // Final revision
    // String[] details this fragment is representing
    private String[] details;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment()
    {
    }

    // Method name:     onCreate
    // Arguments:       Bundle savedInstanceState - previous state
    // description:     called to create the fragment before onCreateView
    // Calling methods: Java runtime
    // Methods called:  none
    // Returns:         void
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // test if key is in arguments
        if (getArguments().containsKey(ARG_ITEM_ID))
        {
            // Final revision
            // get the String[] detail from the fragment argument
            details = getArguments().getStringArray(ARG_ITEM_ID);

            // get the activity
            Activity activity = this.getActivity();

            // set the content to the toolbar
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                // Final revision
                // name is in index 0 in array
                appBarLayout.setTitle(details[0]);
            }
        }
    }

    // Method name:     onCreateView
    // Arguments:       LayoutInflater inflater - inflater
    //                  ViewGroup container - parent container
    //                  Bundle savedInstanceState - previous state
    // description:     called to create the fragment after onCreate
    // Calling methods: Java runtime
    // Methods called:  none
    // Returns:         void
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (details != null)
        {
            // Final revision
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(
                            details[0] + "\n" +
                            details[1] + "\n" +
                            details[2] + "\n" +
                            details[3] + "\n" +
                            details[4] + "\n"
            );
        }

        return rootView;
    }
}
