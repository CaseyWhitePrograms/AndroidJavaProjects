// Project:         Lab12
// Class:           DummyContent
// Author:          Kozlevcar
// Date:            11/25/18
// Description:     Document all methods in all classes with the following comments

// Method name:
// Arguments:
// description:
// Calling methods:
// Methods called:
// Returns:

package com.example.jkozlevcar.lab12.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent
{
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    // static block of code is run when class is loaded in Java VM
    static
    {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++)
        {
            addItem(createDummyItem(i));
        }
    }

    // Method name:     AddItem
    // Arguments:       Dummy item to be added
    // description:     Adds a dummyItem to the both the arrayList and map.
    // Calling methods: None
    // Methods called:  None
    // Returns:         void
    private static void addItem(DummyItem item)
    {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    // Method name:     CreateDummyItem
    // Arguments:       int position - position in recyclerView
    // description:     Creates a DummyItem (Item #) where # is postion
    // Calling methods: AddItem
    // Methods called:  DummyItem constructor and makeDetails
    // Returns:         DummyItem object
    private static DummyItem createDummyItem(int position)
    {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    // Method name:     makeDetails
    // Arguments:       int position
    // description:     Use a for lops to generate number of details from 1 to position
    // Calling methods: createDummy()
    // Methods called:  None
    // Returns:         String representing details generated in loop
    private static String makeDetails(int position)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++)
        {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem
    {
        // 1,2,3 ...
        public final String id;
        // Item 1, Item 2, Item 3 ...
        public final String content;
        // Details about Item: 1
        // More details information here
        public final String details;

        // Method name:     DummyItem constructor
        // Arguments:       String id, String content, String details
        // description:     class representing an item of dummy content
        // Calling methods: createDummyItem
        // Methods called:  None
        // Returns:         Nothing
        public DummyItem(String id, String content, String details)
        {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        // Method name:     toString
        // Arguments:       None
        // description:     String representation of the object using content field
        // Calling methods: None, can't find where this is called from
        // Methods called:  None
        // Returns:         String dummyItem content field
        @Override
        public String toString()
        {
            return content;
        }
    }
}
