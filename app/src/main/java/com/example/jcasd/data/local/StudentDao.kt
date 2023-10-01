package com.example.jcasd.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

// step 11 create a dao interface, rename it to your liking
@Dao
interface StudentDao {

    @Upsert
    suspend fun upsertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student ORDER BY first_name DESC")
    fun getStudentsByFirstName(): Flow<List<Student>>

    @Query("SELECT * FROM student ORDER BY last_name DESC")
    fun getStudentsByLastName(): Flow<List<Student>>

    @Query("SELECT * FROM student ORDER BY klass DESC")
    fun getStudentsByKlass(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudent(id: Int): Flow<Student>
}