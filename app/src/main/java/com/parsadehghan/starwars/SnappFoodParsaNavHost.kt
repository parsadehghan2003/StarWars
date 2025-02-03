package com.parsadehghan.starwars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.parsadehghan.starwars.people.ui.GRAPH_PEOPLE_ROUTE
import com.parsadehghan.starwars.people.ui.navigateToPeopleDetailScreen
import com.parsadehghan.starwars.people.ui.peopleGraph

@Composable
fun StarWarsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = GRAPH_PEOPLE_ROUTE.route,
        modifier = modifier,
    ) {
        peopleGraph(
            onPeopleClick = { rootRoute, peopleId ->
                navController.navigateToPeopleDetailScreen(rootRoute,peopleId)
            }
        )
    }

}