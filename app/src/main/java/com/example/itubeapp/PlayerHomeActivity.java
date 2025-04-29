package com.example.itubeapp;

// Import Android libraries
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Import database classes for saving playlist entries
import com.example.itubeapp.data.AppDatabase;
import com.example.itubeapp.data.PlaylistItem;

/**
 * PlayerHomeActivity is the main "Home" screen after login.
 *
 * It lets users:
 * - Enter a YouTube video URL
 * - Play the video
 * - Save the video URL to a playlist
 * - View their playlist
 */
public class PlayerHomeActivity extends AppCompatActivity {

    // Declare EditText and Button views
    private EditText etVideoUrl;
    private Button btnPlayVideo, btnAddToPlaylist, btnMyPlaylist;

    // This method is called when the screen is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this screen using activity_player_home.xml
        setContentView(R.layout.activity_player_home);

        // Link the UI elements with their IDs from XML
        etVideoUrl       = findViewById(R.id.etVideoUrl);
        btnPlayVideo     = findViewById(R.id.btnPlayVideo);
        btnAddToPlaylist = findViewById(R.id.btnAddToPlaylist);
        btnMyPlaylist    = findViewById(R.id.btnMyPlaylist);

        // Handle "Play Video" button click
        btnPlayVideo.setOnClickListener(v -> {
            // Get the URL typed by the user
            String url = etVideoUrl.getText().toString().trim();

            // Check if the URL field is empty
            if (url.isEmpty()) {
                // Show a message telling the user to enter a URL
                Toast.makeText(this, "Enter a video URL", Toast.LENGTH_SHORT).show();
            } else {
                // If a URL is entered, move to the VideoPlaybackActivity
                Intent i = new Intent(this, VideoPlaybackActivity.class);
                i.putExtra("videoUrl", url); // Pass the URL to the next screen
                startActivity(i);
            }
        });

        // Handle "Add to Playlist" button click
        btnAddToPlaylist.setOnClickListener(v -> {
            // Get the URL typed by the user
            String url = etVideoUrl.getText().toString().trim();

            // Check if the URL field is empty
            if (url.isEmpty()) {
                // Show a message telling the user to enter a URL
                Toast.makeText(this, "Enter a video URL", Toast.LENGTH_SHORT).show();
            } else {
                // If URL is entered, save it into the Room database
                AppDatabase db = AppDatabase.getInstance(this); // Get the database instance
                db.playlistDao().insert(new PlaylistItem(url)); // Insert new PlaylistItem
                Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle "My Playlist" button click
        btnMyPlaylist.setOnClickListener(v ->
                // Open the PlaylistActivity to show saved videos
                startActivity(new Intent(this, PlaylistActivity.class))
        );
    }
}
