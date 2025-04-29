package com.example.itubeapp;

// Import  Android and app-specific libraries
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.itubeapp.data.AppDatabase;
import com.example.itubeapp.data.PlaylistItem;
import com.example.itubeapp.ui.PlaylistAdapter;

import java.util.List;

/**
 * PlaylistActivity shows the list of saved playlist URLs to the user.
 *
 * The list is displayed vertically using a RecyclerView.
 */
public class PlaylistActivity extends AppCompatActivity {

    // Declare a RecyclerView for displaying the playlist
    private RecyclerView rvPlaylist;

    // This method is called when the screen (Activity) is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this screen using activity_playlist.xml
        setContentView(R.layout.activity_playlist);

        // Find the RecyclerView in the layout
        rvPlaylist = findViewById(R.id.rvPlaylist);

        // Set the RecyclerView to use a LinearLayoutManager (vertical scrolling list)
        rvPlaylist.setLayoutManager(new LinearLayoutManager(this));

        // Load and display the playlist from the database
        loadPlaylist();
    }

    // This method loads all playlist items from the database and displays them
    private void loadPlaylist() {
        // Get an instance of the Room database
        AppDatabase db = AppDatabase.getInstance(this);

        // Fetch all saved playlist items
        List<PlaylistItem> items = db.playlistDao().getAll();

        // Create a new PlaylistAdapter to display the items
        PlaylistAdapter adapter = new PlaylistAdapter(
                items,
                new PlaylistAdapter.OnDeleteClick() {
                    @Override
                    public void onDelete(PlaylistItem item) {
                        // When delete button is clicked, remove the item from the database
                        db.playlistDao().delete(item);

                        // Refresh the playlist view after deleting an item
                        loadPlaylist();
                    }
                }
        );

        // Set the adapter to the RecyclerView to show the list
        rvPlaylist.setAdapter(adapter);
    }
}
