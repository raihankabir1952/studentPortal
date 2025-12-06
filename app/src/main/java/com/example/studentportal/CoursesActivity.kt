package com.example.studentportal

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CoursesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"
        val courseContainer = findViewById<LinearLayout>(R.id.courseContainer)

        val sharedPrefs = getSharedPreferences("student_courses", MODE_PRIVATE)
        val courses = sharedPrefs.getStringSet(studentName, setOf())

        if (!courses.isNullOrEmpty()) {
            for (course in courses) {
                val textView = TextView(this)
                textView.text = course
                textView.textSize = 18f
                textView.setTextColor(ContextCompat.getColor(this, R.color.purple_700))
                textView.setPadding(10, 10, 10, 10)
                courseContainer.addView(textView)
            }
        } else {
            val textView = TextView(this)
            textView.text = "No courses selected yet."
            textView.textSize = 18f
            textView.setTextColor(ContextCompat.getColor(this, R.color.black))
            courseContainer.addView(textView)
        }
    }
}
