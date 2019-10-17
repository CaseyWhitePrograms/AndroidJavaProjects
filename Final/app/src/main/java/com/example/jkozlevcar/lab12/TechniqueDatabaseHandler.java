// Project:     Java III Project
// Author:      White
// Date:        3/5/19
// File:        DatabaseHandler.java
// Description: This class handles all database activity

package com.example.jkozlevcar.lab12;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TechniqueDatabaseHandler extends SQLiteOpenHelper
{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "technique";

    // table names
    private static final String TABLE_TECHNIQUE = "techniqueTrained";

    // course Table columns names
    private static final String COL_POSITION = "_position";
    private static final String COL_ATTACK = "attack";
    private static final String COL_RANK = "rank";
    private static final String COL_HOURS = "Hours";
    private static final String COL_DATE = "date";

    // references needed for shared preferences
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private Context context;    // used by preferences

    // constructor
    public TechniqueDatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // context needed by preferences
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        System.out.println("SQLite onCreate called");

        // SQLite Create syntax
        //CREATE TABLE NameOfTable(Column1 Type, Column2 Type);

        // declare string with SQL command to create the technique table
        String CREATE_TECHNIQUE_TABLE = "CREATE TABLE " + TABLE_TECHNIQUE + "("
                + COL_POSITION + " STRING PRIMARY KEY,"
                + COL_ATTACK + " TEXT,"
                + COL_RANK + " TEXT,"
                + COL_HOURS + " TEXT,"
                + COL_DATE + " TEXT"
                + ")";

        //Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(CREATE_TECHNIQUE_TABLE);
    }

    // add a position to the technique table
    public void addTechnique(TechniquePosition techniqueTrained)
    {
        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_POSITION, techniqueTrained.getPosition());                    // technique postion
        values.put(COL_ATTACK, techniqueTrained.getAttack());                  // technique attack
        values.put(COL_RANK, techniqueTrained.getRank());                  // technique rank
        values.put(COL_HOURS, techniqueTrained.getHours());   // technique time
        values.put(COL_DATE, techniqueTrained.getDate());     // technique date

        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_TECHNIQUE, null, values);
        db.close(); // Closing database connection
    }

    public void updateTechnique(TechniquePosition techniqueTrained)
    {
        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();


        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be updated into the matching columns
        ContentValues values = new ContentValues();
        //values.put(COL_POSITION, contact.getName());                    // position name
        values.put(COL_ATTACK, techniqueTrained.getAttack());                  // Attack name
        values.put(COL_RANK, techniqueTrained.getRank());                  // rank
        values.put(COL_HOURS, techniqueTrained.getHours());   // technique time
        values.put(COL_DATE, techniqueTrained.getDate());     // technique date


        // use name as arguments
        String[] args = new String[]{techniqueTrained.getPosition()};

        // update convenience command
        db.update(TABLE_TECHNIQUE, values, COL_POSITION + "=?", args);

    }

    // get ContactBusiness entry from business table and return as ContactBusiness object
    public TechniquePosition getTechniqueTrainedByPosition(String someName)
    {
        // create reference to ContactBusiness for return
        TechniquePosition position = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_TECHNIQUE, new String[]{COL_POSITION,
                            COL_ATTACK, COL_RANK, COL_HOURS, COL_DATE}, COL_POSITION + "=?",
                    new String[]{someName}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create TechniquePosition object from cursor
                position = new TechniquePosition(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
            } else
            {
                System.out.println("getTechniquePositionByName cursor is null");
                position = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return position;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return position;
        }

    }
    // delete a techniqueTrained by name from the technique table
    public void deleteTechnique(String someName)
    {
        // get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // use SQLite convenience method to delete
        // delete(String table, String whereClause, String[] whereArgs)
        db.delete(TABLE_TECHNIQUE, COL_POSITION + " = ?",
                new String[] { someName });

        db.close(); // Close database connection
    }

    // get all techniqueTrained from the technique table
    public Cursor getAllTechniqueTrained()
    {
        // set up preferences with name and in private mode
        preferenceSettings = context.getSharedPreferences("project", PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();
        String pref = preferenceSettings.getString("DEFAULT_ORDER", "ASC");
        System.out.println("DatabaseHandler Default_Order: " + pref);

        // use preference to determine ascending or descending
        String selectQuery = "SELECT *" +
                " FROM " + TABLE_TECHNIQUE + " ORDER BY " + COL_POSITION + (pref.equals("DESC") ? " DESC" : " ASC");


        //get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        //execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        //return the Cursor
        return cursor;
    }

    // delete all the friend contact entries in the friend table
    public void deleteAllTechniqueTrained()
    {
        // get the datatbase
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the friend contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_TECHNIQUE, null, null);
        db.close(); // Closing database connection
    }


    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables, add tables,
    // or do anything else it needs to upgrade to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("SQLite onUpgrade called");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNIQUE);

        // Create tables again
        onCreate(db);
    }

    // Called when the database needs to be downgraded.
    // This is strictly similar to onUpgrade(SQLiteDatabase, int, int) method,
    // but is called whenever current version is newer than requested one.
    // However, this method is not abstract, so it is not mandatory for a customer to implement it.
    // If not overridden, default implementation will reject downgrade and throws SQLiteException
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("SQLite onDowngrade called");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNIQUE);

        // Create tables again
        onCreate(db);
    }
}

