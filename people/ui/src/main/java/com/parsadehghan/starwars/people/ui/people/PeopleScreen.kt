package com.parsadehghan.starwars.people.ui.people

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PeopleScreen(
    peopleId: String,
    onBackClick: () -> Unit,
) {
    val viewModel: PeopleViewModel = hiltViewModel()
    viewModel.loadPeople(peopleId)
    val state by viewModel.uiState.collectAsState()
    state.characterDetail?.let { person ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = person.name,
                style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DetailItem(label = "Height", value = person.height)
            DetailItem(label = "Mass", value = person.mass)
            DetailItem(label = "Hair Color", value = person.hairColor)
            DetailItem(label = "Skin Color", value = person.skinColor)
            DetailItem(label = "Eye Color", value = person.eyeColor)
            DetailItem(label = "Birth Year", value = person.birthYear)
            DetailItem(label = "Gender", value = person.gender)
        }
    }

}

@Composable
fun DetailItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}