@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jcasd.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jcasd.data.local.ScreenEvent
import com.example.jcasd.data.local.Student
import com.example.jcasd.data.local.UiState

@Composable
fun HomeScreen(
    state: UiState,
    onEvent: (ScreenEvent) -> Unit,
    onNavigateToAdd: () -> Unit,
    onNavigateToDetail: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick =  onNavigateToAdd ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add button"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn {
                items(state.students) { item ->
                    StudentItem(
                        studentItem = item,
                        onEvent = onEvent,
                        onNavigateToDetail = onNavigateToDetail
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentItem(
    studentItem: Student,
    onEvent: (ScreenEvent) -> Unit,
    onNavigateToDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            onEvent(ScreenEvent.SetSelectedId(studentItem.id))
            onNavigateToDetail()
        },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            AsyncImage(
                model = Uri.parse(studentItem.image),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = studentItem.firstName)
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "detail icons"
            )
        }
    }
}