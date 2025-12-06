package com.example.studentportal

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private val selectedCourses = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Get student name from intent
        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"

        // SharedPreferences for saving courses
        val sharedPreferences: SharedPreferences = getSharedPreferences("student_courses", MODE_PRIVATE)

        // Course list container
        val courseContainer = findViewById<LinearLayout>(R.id.courseContainer)

        // Dummy courses
        val courses = listOf(
            "CSE 101 - Introduction to Programming",
            "CSE 202 - Data Structures",
            "MAT 111 - Calculus I",
            "PHY 101 - Physics I",
            "ENG 101 - English Composition"
        )

        // Dynamically create checkboxes for courses
        for (course in courses) {
            val checkBox = CheckBox(this)
            checkBox.text = course
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) selectedCourses.add(course)
                else selectedCourses.remove(course)
            }
            courseContainer.addView(checkBox)
        }

        // OK Button
        val btnOk = findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener {
            if (selectedCourses.isNotEmpty()) {
                // Save selected courses in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putStringSet(studentName, selectedCourses.toSet())
                editor.apply()

                Toast.makeText(this, "Courses Selected: ${selectedCourses.joinToString()}", Toast.LENGTH_LONG).show()
                finish() // close registration
            } else {
                Toast.makeText(this, "Please select at least one course", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
