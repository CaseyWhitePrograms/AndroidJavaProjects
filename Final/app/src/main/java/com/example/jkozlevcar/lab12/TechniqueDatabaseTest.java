// Project:     Java III Project
// Author:      White
// Date:        3/5/19
// File:        DatabaseTest.java
// Description: This class tests creating, adding, deleting and listing of the technique table

package com.example.jkozlevcar.lab12;


import android.content.Context;
import android.database.Cursor;

// class is abstract because there is no need to create an object
// It is only used to hold methods to test the database
public abstract class TechniqueDatabaseTest
{
    // Lab9
    public static void testTechniqueTable(Context context)
    {
        // create the database handler object
        TechniqueDatabaseHandler dbh = new TechniqueDatabaseHandler(context);

        // delete any existing contacts in the friend table
        dbh.deleteAllTechniqueTrained();

        try
        {
            // create position to add to tecnhique table
            TechniquePosition position1 = new TechniquePosition("Position1", "Attack1", "R1", "H1", "D1");
            TechniquePosition position2 = new TechniquePosition("Position2", "Attack2", "R2", "H2", "D2");
            TechniquePosition position3 = new TechniquePosition("Position3", "Attack3", "R3", "H3", "D3");
            TechniquePosition position4 = new TechniquePosition("Position4", "Attack4", "R4", "H4", "D4");
//            TechniquePosition position4 = new TechniquePosition("Position4", "Attack4", "R4", hours: "H2", date: "D4");


            // add the position to the technique table
            dbh.addTechnique(position1);
            dbh.addTechnique(position2);
            dbh.addTechnique(position3);
            dbh.addTechnique(position4);

            // test method getAllTechniqueTrained()
            System.out.println("Test getAllTechniqueTrained()");
            Cursor cursor = dbh.getAllTechniqueTrained();

            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4));
                cursor.moveToNext();
            }
            System.out.println(" ");

            // test method getTechniqueTrainedByPosition()
            System.out.println("Test getTechniqueTrainedByPosition(\"Position3\")");
            TechniquePosition position = dbh.getTechniqueTrainedByPosition("Position3");
            System.out.println(position);
            System.out.println(" ");


            // test deleteTechnique()
            System.out.println("Test deleteTechnique(\"Position3\")");
            dbh.deleteTechnique("P3");
            System.out.println(" ");

            // test updateContactBusinessByName()
            System.out.println("Test updateTechnique(\"Position3\")");
            // change the attack on p4
            position4.setAttack("");
            dbh.updateTechnique(position4);
            System.out.println(" ");


            // move to the first record in the cursor
            cursor.moveToFirst();

            // loop through the cursor

            while (cursor.isAfterLast() == false)
            {
                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "  +
                        cursor.getString(2) + ", " + cursor.getString(3) + ", " +
                        cursor.getString(4));
                cursor.moveToNext();
            }
            System.out.println(" ");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
