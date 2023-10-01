package com.example.jcasd.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// step 10 create an entity data class, rename it to your liking
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String = "",
    @ColumnInfo(name = "last_name")
    val lastName: String = "",
    val klass: String = "",
    val subjects: String = "",
    val gender: String = "",
    val dob: String = "",
    val address: String = "",
    val image: String = ""
)

