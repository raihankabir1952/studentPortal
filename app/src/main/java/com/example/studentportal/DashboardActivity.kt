package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.switchmaterial.SwitchMaterial

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get student info from Intent
        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"
        val studentPassword = intent.getStringExtra("STUDENT_PASSWORD") ?: "1234"

        // Welcome Text
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Welcome, $studentName"

        // Dark Mode Switch
//        val switchTheme = findViewById<SwitchMaterial>(R.id.switchTheme)
//        switchTheme.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
//        switchTheme.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }

        // Buttons
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnCourses = findViewById<Button>(R.id.btnCourses)
        val btnAttendance = findViewById<Button>(R.id.btnAttendance)
        val btnResults = findViewById<Button>(R.id.btnResults)
        val btnNotice = findViewById<Button>(R.id.btnNotice)
        val btnRegistration = findViewById<Button>(R.id.btnRegistration)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Profile
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            startActivity(intent)
        }

        // Courses
        btnCourses.setOnClickListener {
            val intent = Intent(this, CoursesActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            intent.putExtra("STUDENT_PASSWORD", studentPassword)
            startActivity(intent)
        }

        // Attendance
        btnAttendance.setOnClickListener {
            val intent = Intent(this, AttendanceActivity::class.java)
            startActivity(intent)
        }

        // Results
        btnResults.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            startActivity(intent)
        }

        // Notice
        btnNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }

        // Registration
        btnRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            intent.putExtra("STUDENT_PASSWORD", studentPassword)
            startActivity(intent)
        }

        // Logout
        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
    }

