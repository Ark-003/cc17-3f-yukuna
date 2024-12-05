package com.example.yukuna_receivoo.auth.presentation.register

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.yukuna_receivoo.R
import com.example.yukuna_receivoo.auth.presentation.login.Login
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var nameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var termsCheckBox: CheckBox
    private lateinit var registerButton: Button
    private lateinit var googleButton: Button
    private lateinit var facebookButton: Button
    private lateinit var loginTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // Match with XML file name

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Bind views
        nameEditText = findViewById(R.id.name)
        usernameEditText = findViewById(R.id.username)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        termsCheckBox = findViewById(R.id.terms)
        registerButton = findViewById(R.id.registerbutton)
        googleButton = findViewById(R.id.googlebutton)
        facebookButton = findViewById(R.id.facebookbutton)
        loginTextView = findViewById(R.id.loginText)

        // Handle Register Button
        registerButton.setOnClickListener {
            registerUser()
        }

        // Navigate to Login Activity
        loginTextView.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish() // Optional: Remove the current activity from the stack
        }

        // Optional: Handle Google and Facebook buttons
        googleButton.setOnClickListener {
            Toast.makeText(this, "Google Sign Up is under development", Toast.LENGTH_SHORT).show()
        }

        facebookButton.setOnClickListener {
            Toast.makeText(this, "Facebook Sign Up is under development", Toast.LENGTH_SHORT).show()
        }

        // Handle Already Have an Account? Log in
        loginTextView.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    private fun registerUser() {
        val name = nameEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!termsCheckBox.isChecked) {
            Toast.makeText(this, "You must agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase Register with Email and Password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to the login screen or main activity
                    startActivity(Intent(this, Login::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}