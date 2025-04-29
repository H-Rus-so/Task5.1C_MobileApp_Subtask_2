// Package where this file belongs (organised under "data" folder)
package com.example.itubeapp.data;

// Import necessary Android and Room library classes
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This class sets up the Room database for storing playlist items locally on the device.
 *
 * It defines:
 * - The database entity (PlaylistItem)
 * - The version of the database
 * - The DAO (Data Access Object) that will manage database operations
 */
@Database(entities = {PlaylistItem.class}, version = 1) // Declare this as a Room database with PlaylistItem entity, version 1
public abstract class AppDatabase extends RoomDatabase { // Extend RoomDatabase to inherit database functionalities

    // Singleton instance: only one instance of the database will exist at a time
    private static AppDatabase INSTANCE;

    // Abstract method that gives access to PlaylistDao (for performing database operations)
    public abstract PlaylistDao playlistDao();

    // Static method to get the one and only instance of the database
    public static AppDatabase getInstance(Context context) {
        // Check if INSTANCE is null (i.e., database not created yet)
        if (INSTANCE == null) {
            // Create a new database instance
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), // Use the application context (not an Activity context) to avoid memory leaks
                            AppDatabase.class,               // Reference to this database class
                            "itube_db"                       // Name of the database file ("itube_db")
                    )
                    .allowMainThreadQueries() // Allow database operations on the main thread
                    .build(); // Build the database
        }
        // Return the database instance
        return INSTANCE;
    }
}
