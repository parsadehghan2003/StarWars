package com.parsadehghan.starwars.people.ui.people_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.parsadehghan.starwars.core.Character
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest


@Composable
fun PeopleListScreen(
    onPeopleClick: (String) -> Unit,
    viewModel: PeopleListViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    CharacterListView(
        state,
        onLoadMore = { viewModel.loadNextPage() },
        onCharacterClick = { onPeopleClick(it.characterUrl) },
        onSearchValueChange = { viewModel.searchPeople(it) }
    )

}

@Composable
fun CharacterListView(
    peopleListUiState: PeopleListUiState,
    onSearchValueChange: (String) -> Unit,
    onLoadMore: () -> Unit,         // Callback to fetch more data
    onCharacterClick: (Character) -> Unit // Callback for item click
) {
    val query = remember { mutableStateOf("") }

    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
        }.collectLatest { isAtEnd ->
            if (isAtEnd) onLoadMore.invoke()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Star Wars Characters") })
        }
    ) { padding ->
        Column (modifier = Modifier.fillMaxWidth().padding(padding)) {
            OutlinedTextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                    onSearchValueChange(it)
                },
                label = { Text("Search People") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                if (peopleListUiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                } else
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(peopleListUiState.listOfPeoples, key = { it.characterUrl }) { character ->
                            CharacterItemView(
                                character = character,
                                onClick = { onCharacterClick(character) }
                            )
                        }

                        if (peopleListUiState.isLoadingMore && peopleListUiState.hasMore) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )
                            }
                        }
                        // Show loading indicator for pagination

                    }
            }
        }
        }

}

@Composable
fun CharacterItemView(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for character avatar
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = character.characterName.firstOrNull()?.toString() ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Character Name
            Column {
                Text(
                    text = character.characterName,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Birth Year: ${character.birthYear}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}