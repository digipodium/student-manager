@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jcasd.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jcasd.data.local.ScreenEvent
import com.example.jcasd.data.local.UiState

@Composable
fun AddScreen(
    state: UiState,
    onEvent: (ScreenEvent) -> Unit,
    onNavigateUp: () -> Unit,
) {

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            Log.d("Tag", "onActivityResult: $uri")
            onEvent(ScreenEvent.SetImage(uri!!))

        }
    )

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.image == null) {
            IconButton(onClick = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
        } else {
            AsyncImage(
                model = state.image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
        OutlinedTextField(value = state.firstName,
            onValueChange = { onEvent(ScreenEvent.SetFirstName(it)) },
            placeholder = { Text(text = "Enter First Name") })

        OutlinedTextField(value = state.lastName,
            onValueChange = { onEvent(ScreenEvent.SetLastName(it)) },
            placeholder = { Text(text = "Enter Last Name") })

        OutlinedTextField(value = state.klass,
            onValueChange = { onEvent(ScreenEvent.SetKlass(it)) },
            placeholder = { Text(text = "Enter Class") })

        OutlinedTextField(value = state.subjects,
            onValueChange = { onEvent(ScreenEvent.SetSubjects(it)) },
            placeholder = { Text(text = "Enter Subjects") })

        OutlinedTextField(value = state.dob,
            onValueChange = { onEvent(ScreenEvent.SetDob(it)) },
            placeholder = { Text(text = "Enter Date of Birth") })

        OutlinedTextField(value = state.gender,
            onValueChange = { onEvent(ScreenEvent.SetGender(it)) },
            placeholder = { Text(text = "Enter Your Gender") })

        OutlinedTextField(value = state.address,
            onValueChange = { onEvent(ScreenEvent.SetAddress(it)) },
            placeholder = { Text(text = "Enter Your Address") })
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(10.dp)
        ) {
            Button(
                onClick = { onEvent(ScreenEvent.SaveStudent) },
                modifier = Modifier.weight(0.1f)
            ) {
                Text(text = "Save")
            }
        }
    }
}

