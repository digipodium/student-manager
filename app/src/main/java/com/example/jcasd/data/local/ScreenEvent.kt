package com.example.jcasd.data.local

// step 10 create an event interface, rename it to your liking
sealed interface ScreenEvent {
    // form events
    object SaveStudent : ScreenEvent

    data class SetFirstName(val firstName: String) : ScreenEvent
    data class SetLastName(val lastName: String) : ScreenEvent
    data class SetKlass(val klass: String) : ScreenEvent
    data class SetSubjects(val subjects: String) : ScreenEvent
    data class SetImage(val image: String) : ScreenEvent
    data class SetGender(val gender: String) : ScreenEvent
    data class SetDob(val dob: String) : ScreenEvent
    data class SetAddress(val address: String) : ScreenEvent
    // list events
    data class SetSortType(val sortType: SortType) : ScreenEvent
    data class SetSelectedId(val id: Int) : ScreenEvent
    // detail events
    data class DeleteStudent(val student: Student) : ScreenEvent
}