package com.example.studentportal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CoursesActivity : AppCompatActivity() {

    private lateinit var studentName: String
    private lateinit var courseContainer: LinearLayout
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"
        courseContainer = findViewById(R.id.courseContainer)
        sharedPrefs = getSharedPreferences("student_courses", MODE_PRIVATE)

        loadCourses()

        // Add Course button
        val btnAddCourse = findViewById<Button>(R.id.btnAddCourse)
        btnAddCourse.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            startActivity(intent)
        }
    }

    private fun loadCourses() {
        courseContainer.removeAllViews()
        val courses = sharedPrefs.getStringSet(studentName, setOf())?.toMutableSet() ?: mutableSetOf()

        if (courses.isEmpty()) {
            val tvEmpty = TextView(this)
            tvEmpty.text = "No courses selected yet."
            tvEmpty.textSize = 18f
            tvEmpty.setTextColor(resources.getColor(R.color.black, theme))
            courseContainer.addView(tvEmpty)
        } else {
            for (course in courses) {
                addCourseView(course)
            }
        }
    }

    private fun addCourseView(courseName: String) {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.HORIZONTAL
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.setPadding(10, 10, 10, 10)

        val tvCourse = TextView(this)
        tvCourse.text = courseName
        tvCourse.textSize = 18f
        tvCourse.setTextColor(resources.getColor(R.color.purple_700, theme))
        tvCourse.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)

        val btnDelete = Button(this)
        btnDelete.text = "Delete"
        btnDelete.setOnClickListener {
            // Remove course from SharedPreferences
            val courses = sharedPrefs.getStringSet(studentName, setOf())?.toMutableSet() ?: mutableSetOf()
            courses.remove(courseName)
            sharedPrefs.edit().putStringSet(studentName, courses).apply()
            // Remove from UI
            courseContainer.removeView(layout)
        }

        layout.addView(tvCourse)
        layout.addView(btnDelete)
        courseContainer.addView(layout)
    }

    override fun onResume() {
        super.onResume()
        loadCourses() // Refresh courses after returning from RegistrationActivity
    }
}
