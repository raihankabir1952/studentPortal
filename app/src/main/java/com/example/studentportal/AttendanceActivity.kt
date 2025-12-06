package com.example.studentportal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_attendance)

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Page title
        val tvTitle = findViewById<TextView>(R.id.tvAttendanceTitle)
        tvTitle.text = "Attendance"

        // Total attendance (later dynamic)
        val tvTotal = findViewById<TextView>(R.id.tvTotalAttendance)
        tvTotal.text = "Total Attendance: 89%"
    }
}
