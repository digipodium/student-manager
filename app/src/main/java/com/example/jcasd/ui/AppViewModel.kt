package com.example.jcasd.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcasd.data.local.ResponseType
import com.example.jcasd.data.local.ScreenEvent
import com.example.jcasd.data.local.Student
import com.example.jcasd.data.local.StudentDao
import com.example.jcasd.data.local.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// step 14 create a view model class, rename it to your liking
class AppViewModel(
    private val dao: StudentDao
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            dao.getStudentsByFirstName().collect { students ->
                _state.update { it.copy(students = students) }
            }
        }
    }

    fun onEvent(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.DeleteStudent -> viewModelScope.launch { dao.deleteStudent(event.student) }
            is ScreenEvent.SetAddress -> _state.update { it.copy(address = event.address) }
            is ScreenEvent.SetDob -> _state.update { it.copy(dob = event.dob) }
            is ScreenEvent.SetFirstName -> _state.update { it.copy(firstName = event.firstName) }
            is ScreenEvent.SetGender -> _state.update { it.copy(gender = event.gender) }
            is ScreenEvent.SetImage -> _state.update { it.copy(image = event.image) }
            is ScreenEvent.SetKlass -> _state.update { it.copy(klass = event.klass) }
            is ScreenEvent.SetLastName -> _state.update { it.copy(lastName = event.lastName) }
            is ScreenEvent.SetSelectedId -> setSelectedStudent(event.id)
            is ScreenEvent.SetSortType -> _state.update { it.copy(sortOrder = event.sortType) }
            is ScreenEvent.SetSubjects -> _state.update { it.copy(subjects = event.subjects) }
            ScreenEvent.SaveStudent -> saveStudent()
        }
    }

    private fun setSelectedStudent(id: Int) {
        viewModelScope.launch {
            dao.getStudent(id).collect { student ->
                _state.update { it.copy(selectedStudent = student) }
            }
        }
    }

    private fun saveStudent() {
        val newStudent = Student(
            firstName = _state.value.firstName,
            lastName = _state.value.lastName,
            klass = _state.value.klass,
            subjects = _state.value.subjects,
            gender = _state.value.gender,
            dob = _state.value.dob,
            address = _state.value.address,
            image = _state.value.image.toString()
        )
        if (validateInput(newStudent)) {
            viewModelScope.launch {
                try {
                    dao.upsertStudent(newStudent)
                    _state.update { it.copy(responseType = ResponseType.SUCCESS) }
                } catch (e: Exception) {
                    _state.update { it.copy(responseType = ResponseType.ERROR) }
                }
            }
        } else {
            _state.update { it.copy(responseType = ResponseType.ERROR) }
        }
    }

    private fun validateInput(student: Student): Boolean {
        if (student.firstName.isEmpty() || student.dob.isEmpty() || student.address.isEmpty() || student.image.isEmpty()) {
            return false
        }
        return true
    }

}