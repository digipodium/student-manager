package com.example.jcasd.data.local

import android.net.Uri

// step 13 create a ui state data class, rename it to your liking

enum class SortType {
    FIRST_NAME,
    LAST_NAME,
    CLASS
}

enum class ResponseType {
    SUCCESS,
    ERROR,
    LOADING,
    EMPTY
}

data class UiState(
    val students: List<Student> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val klass: String = "",
    val subjects: String = "",
    val gender: String = "",
    val dob: String = "",
    val address: String = "",
    val image: Uri? = null,
    val selectedStudent: Student = Student(),
    val sortOrder: SortType = SortType.FIRST_NAME,
    val responseType: ResponseType = ResponseType.EMPTY,
)
