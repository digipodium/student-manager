package com.example.jcasd.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jcasd.data.local.ScreenEvent
import com.example.jcasd.data.local.UiState

@Composable
fun DetailScreen(
    state: UiState,
    onEvent: (ScreenEvent) -> Unit,
) {
    val std = state.selectedStudent
    Log.d("Tag", std.image)
    Column {
        Card {
            AsyncImage(
                model = Uri.parse(std.image),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = "Student name")
                Text(text = std.firstName + std.lastName)
            }
            Column {
                Text(text = "Class name")
                Text(text = std.klass)
            }
            Column {
                Text(text = "Subjects")
                Text(text = std.subjects)
            }
            Column {
                Text(text = "Gender")
                Text(text = std.gender)
            }
            Column {
                Text(text = "Date of Birth")
                Text(text = std.dob)
            }
            Column {
                Text(text = "Address")
                Text(text = std.address)
            }
        }
        Column {
//            iska Logic ->
            Button(onClick = { }) {
                Text(text = "Edit")
            }
            OutlinedButton(onClick = {  }) {
                Text(text = "Done")
            }
        }
    }
}