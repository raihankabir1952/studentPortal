package com.example.studentportal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        // Receive the student name from DashboardActivity
        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"

        // Find Views
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val tvProfileID = findViewById<TextView>(R.id.tvProfileID)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvDept = findViewById<TextView>(R.id.tvDept)
        val tvSemester = findViewById<TextView>(R.id.tvSemester)

        // Set data
        tvProfileName.text = studentName          // Name from dashboard
        tvProfileID.text = "ID: 2025-${studentName.length}-12"  // Example auto ID
        tvEmail.text = "Email: ${studentName.lowercase()}@student.com"
        tvPhone.text = "Phone: 01XXXXXXXXX"
        tvDept.text = "Department: CSE"
        tvSemester.text = "Semester: 9th"
    }
}
