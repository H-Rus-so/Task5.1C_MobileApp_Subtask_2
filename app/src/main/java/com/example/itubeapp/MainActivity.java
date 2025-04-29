package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * MainActivity is the first screen that shows when the app launches.
 *
 * It allows users to:
 * - Enter username and password to log in
 * - OR click to go to the sign-up page
 */
public class MainActivity extends AppCompatActivity {

    // Declare EditText fields for username and password
    private EditText etUsername, etPassword;

    // Declare Buttons for Login and navigating to Register screen
    private Button btnLogin, btnGoToRegister;

    // This method is called when the screen is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this screen using activity_auth_login.xml
        setContentView(R.layout.activity_auth_login);

        // Find the EditText and Button views by their IDs from the XML
        etUsername      = findViewById(R.id.etUsername);
        etPassword      = findViewById(R.id.etPassword);
        btnLogin        = findViewById(R.id.btnLogin);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);

        // Set a click listener for the LOGIN button
        btnLogin.setOnClickListener(v -> {
            // Get the text entered by the user and remove extra spaces
            String u = etUsername.getText().toString().trim();
            String p = etPassword.getText().toString().trim();

            // Check if username or password fields are empty
            if (u.isEmpty() || p.isEmpty()) {
                // Show a message telling the user to fill both fields
                Toast.makeText(this, "Enter both fields", Toast.LENGTH_SHORT).show();

            } else {
                // If not empty, open the PlayerHomeActivity (Home screen)
                startActivity(new Intent(this, PlayerHomeActivity.class));

                // Close the login screen so users can't return to it by pressing back
                finish();
            }
        });

        // Set a click listener for the SIGN UP button
        btnGoToRegister.setOnClickListener(v ->
                // Open the AuthRegisterActivity (Sign-up screen)
                startActivity(new Intent(this, AuthRegisterActivity.class))
        );
    }
}
