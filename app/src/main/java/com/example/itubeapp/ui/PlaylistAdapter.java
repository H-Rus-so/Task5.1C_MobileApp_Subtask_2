// Package where this file is stored (organized under "ui" folder)
package com.example.itubeapp.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itubeapp.R;
import com.example.itubeapp.data.PlaylistItem;

import java.util.List;

/**
 * This class defines the Adapter for the RecyclerView that displays playlist items.
 *
 * An Adapter helps connect the data (playlist URLs) to the list UI on the screen.
 */
public class PlaylistAdapter
        extends RecyclerView.Adapter<PlaylistAdapter.VH> { // VH = ViewHolder class

    // Interface to handle clicks on the delete button
    public interface OnDeleteClick {
        void onDelete(PlaylistItem item); // Will be triggered when user clicks "Delete"
    }

    // List that holds all the PlaylistItems we want to display
    private final List<PlaylistItem> items;

    // Listener to handle delete button clicks
    private final OnDeleteClick deleteListener;

    // Constructor: takes in the list of PlaylistItems and the delete listener
    public PlaylistAdapter(List<PlaylistItem> items, OnDeleteClick deleteListener) {
        this.items = items;
        this.deleteListener = deleteListener;
    }

    // This method creates a new ViewHolder when needed
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate (create) a view from item_playlist_entry.xml layout
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_playlist_entry, parent, false);
        // Return a new ViewHolder (VH) that holds this view
        return new VH(v);
    }

    // This method binds (connects) data to the views (called every time an item is shown)
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // Get the PlaylistItem at the current position
        PlaylistItem item = items.get(position);

        // Set the text of the TextView to the URL from the PlaylistItem
        holder.tvUrl.setText(item.url);

        // Set a click listener on the delete button
        // When clicked, it triggers the deleteListener's onDelete method with the current item
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDelete(item));
    }

    // This method returns the total number of items in the list
    @Override
    public int getItemCount() {
        return items.size(); // Simply return how many items there are
    }

    // This is the ViewHolder class (VH)
    // A ViewHolder holds references to the views for each list item (like TextView and Button)
    static class VH extends RecyclerView.ViewHolder {
        TextView tvUrl;       // TextView to display the URL
        ImageButton btnDelete; // Button to delete the item

        // Constructor: links the Java variables to the actual views from XML
        VH(@NonNull View v) {
            super(v);
            tvUrl      = v.findViewById(R.id.tvPlaylistUrl);  // Find the TextView by ID
            btnDelete  = v.findViewById(R.id.btnDeleteEntry); // Find the ImageButton by ID
        }
    }
}
