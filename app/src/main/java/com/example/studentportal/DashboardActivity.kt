package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Handling system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Getting student name
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"
        tvWelcome.text = "Welcome, $studentName"

        // Buttons
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnCourses = findViewById<Button>(R.id.btnCourses)
        val btnAttendance = findViewById<Button>(R.id.btnAttendance)
        val btnResults = findViewById<Button>(R.id.btnResults)
        val btnRegistration = findViewById<Button>(R.id.btnRegistration)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnNotice = findViewById<Button>(R.id.btnNotice)


        // My Profile button
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            startActivity(intent)
        }

        //Courses button
        btnCourses.setOnClickListener {
            val intent = Intent(this, CoursesActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            startActivity(intent)
        }

        //attendance
        btnAttendance.setOnClickListener {
            val intent = Intent(this, AttendanceActivity::class.java)
            startActivity(intent)
        }

        //result part
        btnResults.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            startActivity(intent)
        }

        //registration part
        btnRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra ("STUDENT_NAME", studentName)
            startActivity(intent)
        }

        //notice part
        btnNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }


        //Logout
        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
