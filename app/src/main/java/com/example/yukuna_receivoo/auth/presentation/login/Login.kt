package com.example.yukuna_receivoo.auth.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.yukuna_receivoo.MainActivity
import com.example.yukuna_receivoo.R
import com.example.yukuna_receivoo.auth.presentation.register.Register
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    // Firebase authentication instance
    private lateinit var auth: FirebaseAuth

    // UI components
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var keepLoggedCheckBox: CheckBox
    private lateinit var forgotPasswordText: TextView
    private lateinit var googleButton: Button
    private lateinit var facebookButton: Button
    private lateinit var registerTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Matches the XML file

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Bind UI components to their IDs
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginbutton)
        keepLoggedCheckBox = findViewById(R.id.keeplogged)
        forgotPasswordText = findViewById(R.id.forgotpassword)
        googleButton = findViewById(R.id.googlebutton)
        facebookButton = findViewById(R.id.facebookbutton)
        registerTextView = findViewById(R.id.registerText)

        // Handle login button click
        loginButton.setOnClickListener {
            loginUser()
        }

        // Navigate to Register Activity
        registerTextView.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish() // Optional: Remove the current activity from the stack
        }

        // Handle forgot password click
        forgotPasswordText.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset link sent to $email", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter your email to reset password", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle "Keep me logged in" checkbox
        keepLoggedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Keep logged in feature enabled", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Google login button
        googleButton.setOnClickListener {
            Toast.makeText(this, "Google Login is under development", Toast.LENGTH_SHORT).show()
        }

        // Handle Facebook login button
        facebookButton.setOnClickListener {
            Toast.makeText(this, "Facebook Login is under development", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate input fields
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase authentication for login
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Redirect to the main activity
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}