package com.example.studentportal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CoursesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_courses)

        // Handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ---------- Toolbar Title ----------
        val tvTitle = findViewById<TextView>(R.id.tvCoursesTitle)
        tvTitle.text = "📚 My Courses"

        // ---------- Total Credits ----------
        val tvTotalCredits = findViewById<TextView>(R.id.tvTotalCredits)

        val courseCredits = listOf(3, 3, 3) // CSE101, CSE202, MAT111

        val totalCredits = courseCredits.sum()

        tvTotalCredits.text = "Total Credits: $totalCredits"
    }
}
