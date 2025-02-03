package com.parsadehghan.starwars.people.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.parsadehghan.starwars.people.ui.people_list.PeopleListScreen
import com.parsadehghan.navigator.DestinationRoute
import com.parsadehghan.navigator.composable
import com.parsadehghan.starwars.people.ui.people.PeopleScreen

val GRAPH_PEOPLE_ROUTE = DestinationRoute("people_graph_route")
private const val ROUTE_PEOPLE_LIST_SCREEN = "people_list_screen"
private const val ROUTE_PEOPLE_SCREEN = "people_screen"

fun NavGraphBuilder.peopleGraph(
    onPeopleClick: (rootRoute: DestinationRoute, peopleId: String) -> Unit,
) {
    navigation(
        route = GRAPH_PEOPLE_ROUTE.route,
        startDestination = "${GRAPH_PEOPLE_ROUTE.route}/$ROUTE_PEOPLE_LIST_SCREEN",
    ) {
        composable(
            route = "${GRAPH_PEOPLE_ROUTE.route}/$ROUTE_PEOPLE_LIST_SCREEN",
            screenName = "Home",
        ) {
            PeopleListScreen(
                onPeopleClick = {
                    onPeopleClick(GRAPH_PEOPLE_ROUTE, it)
                }
            )
        }
        composable(
            route = "${GRAPH_PEOPLE_ROUTE.route}/$ROUTE_PEOPLE_SCREEN/{peopleId}",
            screenName = "Home",
        ) {
            val peopleId = it.arguments?.getString("peopleId")

            PeopleScreen (
                onBackClick = {

//                    onPeopleClick(GRAPH_PEOPLE_ROUTE, it)
                }, peopleId = peopleId!!
            )
        }
    }
}
fun NavController.navigateToPeopleDetailScreen(
    rootRoute: DestinationRoute,
    peopleId: String,
) {
    navigate("${rootRoute.route}/$ROUTE_PEOPLE_SCREEN/${peopleId.split("people/")[1].split("/")[0]}")
}
