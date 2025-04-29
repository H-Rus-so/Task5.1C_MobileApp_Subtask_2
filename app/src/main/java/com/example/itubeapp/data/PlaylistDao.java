package com.example.itubeapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * DAO for playlist operations.
 */
@Dao
public interface PlaylistDao {
    @Insert
    void insert(PlaylistItem item);

    @Delete
    void delete(PlaylistItem item);

    @Query("SELECT * FROM playlist")
    List<PlaylistItem> getAll();
}
