package com.example.itubeapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing one saved video URL.
 */
@Entity(tableName = "playlist")
public class PlaylistItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String url;

    public PlaylistItem(String url) {
        this.url = url;
    }
}
