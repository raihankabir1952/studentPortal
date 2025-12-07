
package com.example.studentportal

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private val selectedCourses = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"
        val studentPassword = intent.getStringExtra("STUDENT_PASSWORD") ?: "1234"

        val courseContainer = findViewById<LinearLayout>(R.id.courseContainer)

        val courses = listOf(
            "CSE 101 - Introduction to Programming",
            "CSE 202 - Data Structures",
            "MAT 111 - Calculus I",
            "PHY 101 - Physics I",
            "ENG 101 - English Composition",
            "CSE 102 - Object-Oriented Programming",
            "CSE 303 - Database Systems",
            "CSE 404 - Computer Networks",
            "CSE 505 - Software Engineering",
            "CSE 606 - Artificial Intelligence",
            "CSE 707 - Computer Graphics",
            "CSE 808 - Operating Systems",
            "CSE 909 - Computer Architecture",
            "CSE 100 - Web Programming",
            "CSE 201 - Discrete Mathematics",
            "CSE 302 - Computer Organization",
            "CSE 403 - Computer Networks Lab",
            "CSE 504 - Software Engineering Lab",
            "CSE 605 - Artificial Intelligence Lab",
            "CSE 706 - Computer Graphics Lab"
        )

        val sharedPrefs = getSharedPreferences("student_courses", MODE_PRIVATE)
        val key = "$studentName|$studentPassword" // Use name + password as key
        val savedCourses = sharedPrefs.getStringSet(studentName, setOf()) ?: setOf()


        for (course in courses) {
            val checkBox = CheckBox(this)
            checkBox.text = course
            if (savedCourses.contains(course)) checkBox.isChecked = true
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) selectedCourses.add(course)
                else selectedCourses.remove(course)
            }
            courseContainer.addView(checkBox)
        }

        val btnOk = findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener {
            if (selectedCourses.isNotEmpty()) {
                val allCourses = savedCourses.toMutableSet()
                allCourses.addAll(selectedCourses)
                sharedPrefs.edit().putStringSet(studentName, allCourses).apply()
                Toast.makeText(this, "Courses saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please select at least one course", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
