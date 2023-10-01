package com.example.jcasd.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcasd.data.local.ScreenEvent
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
            is ScreenEvent.SetSelectedId -> _state.update { it.copy(selectedId = event.id) }
            is ScreenEvent.SetSortType -> _state.update { it.copy(sortOrder = event.sortType) }
            is ScreenEvent.SetSubjects -> _state.update { it.copy(subjects = event.subjects) }
            ScreenEvent.SaveStudent -> saveStudent()
        }
    }

    private fun saveStudent() {

    }

}