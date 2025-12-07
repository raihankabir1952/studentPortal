package com.example.studentportal

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CoursesActivity : AppCompatActivity() {

    private lateinit var studentName: String
    private lateinit var studentPassword: String
    private lateinit var userSpecificKey: String
    private lateinit var courseContainer: LinearLayout
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"
        studentPassword = intent.getStringExtra("STUDENT_PASSWORD") ?: ""


        userSpecificKey = "$studentName|$studentPassword"

        courseContainer = findViewById(R.id.courseContainer)
        sharedPrefs = getSharedPreferences("student_courses", MODE_PRIVATE)

        loadCourses()

        val btnAddCourse = findViewById<Button>(R.id.btnAddCourse)
        btnAddCourse.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("STUDENT_NAME", studentName)
            intent.putExtra("STUDENT_PASSWORD", studentPassword)
            startActivity(intent)
        }
    }

    private fun loadCourses() {
        courseContainer.removeAllViews()
        val courses = sharedPrefs.getStringSet(userSpecificKey, setOf())?.toMutableSet() ?: mutableSetOf()

        if (courses.isEmpty()) {
            val tvEmpty = TextView(this)
            tvEmpty.text = "No courses selected yet."
            tvEmpty.textSize = 18f
            tvEmpty.setTextColor(resources.getColor(R.color.black, theme))
            courseContainer.addView(tvEmpty)
        } else {
            courses.sorted().forEach { course ->
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
        btnDelete.backgroundTintList = ColorStateList.valueOf(getColor(R.color.buttonDelete))

        btnDelete.setOnClickListener {
            val currentCourses = sharedPrefs.getStringSet(userSpecificKey, setOf())?.toMutableSet() ?: mutableSetOf()
            currentCourses.remove(courseName)
            sharedPrefs.edit().putStringSet(userSpecificKey, currentCourses).apply()

            courseContainer.removeView(layout)

            if (currentCourses.isEmpty()) {
                loadCourses()
            }
        }

        layout.addView(tvCourse)
        layout.addView(btnDelete)
        courseContainer.addView(layout)
    }

    override fun onResume() {
        super.onResume()
        loadCourses()
    }
}

