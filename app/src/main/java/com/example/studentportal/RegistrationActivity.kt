package com.example.studentportal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {


    private lateinit var courseContainer: LinearLayout
    private lateinit var allCourses: List<String>
    private lateinit var savedCourses: Set<String>
    private val selectedCourses = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"


        courseContainer = findViewById(R.id.courseContainer)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        // SharedPreferences
        val sharedPrefs = getSharedPreferences("student_courses", MODE_PRIVATE)
        savedCourses = sharedPrefs.getStringSet(studentName, setOf()) ?: setOf()
        // selectedCourses add course
        selectedCourses.addAll(savedCourses)


        allCourses = listOf(
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

        // show all course
        displayCourses(allCourses)


        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterCourses(s.toString())
            }
        })

        val btnOk = findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener {

            sharedPrefs.edit().putStringSet(studentName, selectedCourses).apply()
            Toast.makeText(this, "Courses saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // Course filter logic
    private fun filterCourses(query: String) {
        val filteredList = if (query.isEmpty()) {
            allCourses
        } else {
            allCourses.filter { it.contains(query, ignoreCase = true) }
        }
        displayCourses(filteredList)
    }


    private fun displayCourses(coursesToShow: List<String>) {

        val checkBoxesToRemove = mutableListOf<CheckBox>()
        for (i in 0 until courseContainer.childCount) {
            val view = courseContainer.getChildAt(i)
            if (view is CheckBox) {
                checkBoxesToRemove.add(view)
            }
        }

        checkBoxesToRemove.forEach { courseContainer.removeView(it) }


        val indexOfOkButton = courseContainer.indexOfChild(findViewById(R.id.btnOk))
        for (course in coursesToShow.reversed()) {
            val checkBox = CheckBox(this)
            checkBox.text = course
            checkBox.isChecked = selectedCourses.contains(course)

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedCourses.add(course)
                } else {
                    selectedCourses.remove(course)
                }
            }

            courseContainer.addView(checkBox, indexOfOkButton)
        }
    }
}


