package com.example.studentportal

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "theme_prefs"
    private val DARK_MODE_KEY = "dark_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean(DARK_MODE_KEY, false)

        // Apply theme before setContentView
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Dark Mode Switch
        val switchDarkMode = findViewById<Switch>(R.id.switchTheme)
        switchDarkMode.isChecked = isDarkMode

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreferences.edit()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean(DARK_MODE_KEY, true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean(DARK_MODE_KEY, false)
            }
            editor.apply()
        }

        // Login Button
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etUsername = findViewById<EditText>(R.id.etStudentId)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Validation
            if (username.isEmpty()) {
                etUsername.error = "Enter username"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Enter password"
                return@setOnClickListener
            }
            val intent = android.content.Intent(this, DashboardActivity::class.java)
            intent.putExtra("STUDENT_NAME", username)
            intent.putExtra("STUDENT_PASSWORD", password) // <-- এই লাইনটি সবচেয়ে গুরুত্বপূর্ণ
            startActivity(intent)
            finish()
        }
    }
}
