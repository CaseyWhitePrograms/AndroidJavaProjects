//Not used for this project

// Method name:
// Arguments:
// description:
// Calling methods:
// Methods called:
// Returns:

package com.example.jkozlevcar.lab12;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
//public class ItemListActivity extends AppCompatActivity implements AddTechniqueDialog.AddDialogListener,
//        DeleteConfirmDialog.DeleteDialogListener
//{
//
//    // Final revision
//    // context used for database
//    private Context context = this;
//
//    // create the database handler object
//    private TechniqueDatabaseHandler dbh = new TechniqueDatabaseHandler(context);
//
//    // create the cursor reference
//    private Cursor cursor;
//
//    // add reference for adapter
//    private TechniqueMainActivity.SimpleItemRecyclerViewAdapter adapter;
//
//    /**
//     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
//     * device.
//     */
//    private boolean mTwoPane;
//
//    // Method name:     onCreate
//    // Arguments:       Bundle saved instance
//    // description:     creates the item list activity
//    // Calling methods: Android runtime
//    // Methods called:  setupRecyclerView
//    // Returns:         void
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        // inflate the layout
//        setContentView(R.layout.activity_technique_main);
//        // create the toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        // set the toolbar to the activity
//        setSupportActionBar(toolbar);
//        // create the floating action button
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                // create the dialog
//                DialogFragment newFragment = new AddTechniqueDialog();
//
//                // show it
//                newFragment.show(getSupportFragmentManager(), "add");
//            }
//        });
//        // check for two pane container
//        // used for tablet
//        if (findViewById(R.id.item_detail_container) != null)
//        {
//            // The detail container view will be present only in the
//            // large-screen layouts (res/values-w900dp).
//            // If this view is present, then the
//            // activity should be in two-pane mode.
//            mTwoPane = true;
//        }
//
//        // create the recyclerView
//        View recyclerView = findViewById(R.id.item_list);
//
//        // When the system runs the assertion,
//        // it evaluates the Expression and if it is false throws an AssertionError with no detail message.
//        assert recyclerView != null;
//
//        TechniqueDatabaseTest.testTechniqueTable(this);
//
//        // create the cursor using the business table
//        cursor = dbh.getAllTechniqueTrained();
//
//        setupRecyclerView((RecyclerView) recyclerView);
//    }
//    // Method name:     setupRecyclerView
//    // Arguments:       RecyclerView object
//    // description:     Creates and sets the adapter
//    // Calling methods: ItemListActivity OnCreate
//    // Methods called:  SimpleItemRecyclerViewAdapter constructor
//    // Returns:         void
//    private void setupRecyclerView(@NonNull RecyclerView recyclerView)
//    {
//        // Final revision
//        // replace List with Cursor
//        // save adapter in reference
//        adapter = new TechniqueMainActivity.SimpleItemRecyclerViewAdapter(this, cursor, mTwoPane);
//
//        if(adapter != null)
//        {
//            System.out.println("adapter is  not null");
//        }
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public void addDialogPositiveClick(TechniquePosition tpObject)
//    {
//        // add the contact to the database
//        dbh.addTechnique(tpObject);
//
//        // generate a new cursor
//        cursor = dbh.getAllTechniqueTrained();
//
//        // set in a new cursor.
//        adapter.setCursor(cursor);
//
//        // notify the adapter of the change
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void addDialogNegativeClick()
//    {
//
//    }
//
//    @Override
//    public void deleteDialogPositiveClick(String position)
//    {
//        System.out.println("delete " + position);
//        // remove the contact with name from the database
//        dbh.deleteTechnique(position);
//
//        // generate a new cursor
//        cursor = dbh.getAllTechniqueTrained();
//
//        // set in a new cursor.
//        adapter.setCursor(cursor);
//
//        // notify the adapter of the change
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void deleteDialogNegativeClick()
//    {
//
//    }
//
//    // RecyclerView's adapter
//    public static class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<TechniqueMainActivity.SimpleItemRecyclerViewAdapter.ViewHolder>
//    {
//
//        // the parent
//        private final TechniqueMainActivity mParentActivity;
//
//        // Final revision
//        // replace List mValues with cursor
//        private Cursor cursor;
//
//        // boolean determining if app is running on 2 panes, false is 1 pane
//        private final boolean mTwoPane;
//
//        // onClick listener
//        private final View.OnClickListener mOnClickListener = new View.OnClickListener()
//        {
//            // Method name:     onClick
//            // Arguments:       the view causing the click event
//            // description:     handles click event for the adapter, calls detail activity
//            // Calling methods: Android runtime
//            // Methods called:
//            // Returns:         void
//            @Override
//            public void onClick(View view)
//            {
//                // tag contains dummyItem, set in onBindView
////                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//
//                // Final revision
//                // get the String[] details from the tag
//                String[] details = (String[])view.getTag();
//
//                if (mTwoPane)
//                {
////                    // if mTwoPane is true, load fragment for tablets
////                    Bundle arguments = new Bundle();
////                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
////                    ItemDetailFragment fragment = new ItemDetailFragment();
////                    fragment.setArguments(arguments);
////                    mParentActivity.getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.item_detail_container, fragment)
////                            .commit();
//                } else
//                {
//                    // get the context from the view
//                    Context context = view.getContext();
//
//                    // can also cast as activity
//                    TechniqueMainActivity activity = (TechniqueMainActivity)view.getContext();
//
//                    // create an intent for the detail activity
//                    Intent intent = new Intent(context, ItemDetailActivity.class);
//
//                    // Final revision
//                    // put the String[] details in the intent instead of the item.id
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, details);
//
//                    // start the activity
//                    context.startActivity(intent);
//                }
//            }
//        };
//
//        // Final revision
//        // set a new cursor
//        public void setCursor(Cursor newCursor)
//        {
//            cursor = newCursor;
//        }
//
//        // Method name:     SimpleItemRecyclerViewAdapter constructor
//        // Arguments:       ItemListActivity parent - parent activity
//        //                  List<DummyContent.DummyItem> items - list of dummy items
//        //                  boolean twoPane - one pane for phone, 2 for tablet
//        // description:     create the adapter
//        // Calling methods: setupRecyclerView
//        // Methods called:  none
//        // Returns:         nothing
//        // Final revision:  change 2nd arg from List to Cursor
//        SimpleItemRecyclerViewAdapter(TechniqueMainActivity parent,
//                                      Cursor items,
//                                      boolean twoPane)
//        {
//            // Final revision
//            cursor = items;
//
//            // parent actvity
//            mParentActivity = parent;
//
//            // true for tablet and 2 panes
//            mTwoPane = twoPane;
//        }
//
//        // Method name:     onCreateViewHolder
//        // Arguments:       ViewGroup parent - parent which is layout
//        // description:     creates a viewHolder object and inflates its layout
//        // Calling methods: adapter
//        // Methods called:  None
//        // Returns:         ViewHolder object
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
//        {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        // Method name:     onBindViewHolder
//        // Arguments:       ViewHolder holder - holder to bind data to
//        //                  int position - position in recyclerView
//        // description:     binds data to textViews
//        // Calling methods: adapter
//        // Methods called:  none
//        // Returns:         void
//        @Override
//        public void onBindViewHolder(final TechniqueMainActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position)
//        {
//            // Final revision
//            // move the cursor to the position
//            cursor.moveToPosition(position);
//
//            // assign the id from the cursor to the id view
//            holder.mIdView.setText(cursor.getString(0));
//
//            // get the name from the cursor and assign it to content
//            holder.mContentView.setText(cursor.getString(1));
//
//            // set the tag to the dummyItem
//            // itemView is inherited from ViewHolder, root view or layout
//            // Unlike IDs, tags are not used to identify views.
//            // Tags are essentially an extra piece of information that can be associated with a view.
//            // They are most often used as a convenience to store data related to views in
//            // the views themselves rather than by putting them in a separate structure.
////            holder.itemView.setTag(mValues.get(position));
//
//            // Final revision
//            // create String[] details from the cursor columns
//            String[] details = {cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getString(5),
//                    cursor.getString(6)};
//
//            // put the String[] details in the tag
//            holder.itemView.setTag(details);
//
//            // set the onClick listener to the item
//            holder.itemView.setOnClickListener(mOnClickListener);
//
//            // longClick added for delete
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
//            {
//                @Override
//                public boolean onLongClick(View v)
//                {
//                    // get the String[] details from the tag
//                    String[] details = (String[])v.getTag();
//
//                    System.out.println("onLongClick: " + details[0]);
//
//                    // create a bundle object
//                    Bundle args = new Bundle();
//
//                    // add the name selected to the bundle with key name
//                    args.putString("name", details[0]);
//
//                    // create the dialog
//                    DialogFragment newFragment = new DeleteConfirmDialog();
//
//                    // add the args bundle to the dialog fragment
//                    newFragment.setArguments(args);
//
//                    // get the activity from the view
//                    TechniqueMainActivity activity = (TechniqueMainActivity)v.getContext();
//
//                    // show it
//                    newFragment.show(activity.getSupportFragmentManager(), "delete");
//
//                    // true means event was handled
//                    return true;
//                }
//            });
//        }
//
//        // Method name:     getItemCount
//        // Arguments:       none
//        // description:     returns the number of items in the adapter
//        // Calling methods: adapter
//        // Methods called:  none
//        // Returns:         size of data in adapter
//        @Override
//        public int getItemCount()
//        {
//            // Final revision
//            return cursor.getCount();
//        }
//
//        // this class represents a viewHolder, used to make one item in the recyclerView
//        class ViewHolder extends RecyclerView.ViewHolder
//        {
//            final TextView mIdView;
//            final TextView mContentView;
//
//            // constructor
//            // view is the itemView
//            ViewHolder(View view)
//            {
//                super(view);
//
//                // create Java objects for textViews in the viewHolder
//                mIdView = (TextView) view.findViewById(R.id.id_text);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//        }
//}
