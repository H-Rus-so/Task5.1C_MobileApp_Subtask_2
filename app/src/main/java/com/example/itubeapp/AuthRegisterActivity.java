package com.example.itubeapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This Activity is for the Sign-Up screen where users can create a new account.
 */
public class AuthRegisterActivity extends AppCompatActivity {

    // Declare variables for input fields and button
    private EditText etFullName, etNewUsername, etNewPassword, etConfirmPassword;
    private Button btnCreateAccount;

    // This method runs when the screen (Activity) is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this screen using activity_auth_register.xml
        setContentView(R.layout.activity_auth_register);

        // Link the EditText and Button variables to the views in the XML layout
        etFullName        = findViewById(R.id.etFullName);
        etNewUsername     = findViewById(R.id.etNewUsername);
        etNewPassword     = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnCreateAccount  = findViewById(R.id.btnCreateAccount);

        // Set a click listener on the "Create Account" button
        btnCreateAccount.setOnClickListener(v -> {

            // Get the text entered into each field and remove extra spaces
            String fn = etFullName.getText().toString().trim();
            String u  = etNewUsername.getText().toString().trim();
            String p1 = etNewPassword.getText().toString().trim();
            String p2 = etConfirmPassword.getText().toString().trim();

            // Check if any of the fields are empty
            if (fn.isEmpty() || u.isEmpty() || p1.isEmpty() || p2.isEmpty()) {
                // Show a message telling user to fill all fields
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();

                // Check if the two password fields match
            } else if (!p1.equals(p2)) {
                // Show a message saying the passwords don't match
                Toast.makeText(this, "Passwords must match", Toast.LENGTH_SHORT).show();

            } else {
                // If everything is okay, show success message
                Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();

                // Finish this screen and go back to the previous one (Login screen)
                finish();
            }
        });
    }
}
