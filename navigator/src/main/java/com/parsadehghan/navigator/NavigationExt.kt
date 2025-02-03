package com.parsadehghan.navigator

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.composable(
  route: String,
  screenName: String? = null,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit,
) {
  composable(
    route = route,
    arguments = arguments.appendScreenName(screenName),
    deepLinks = deepLinks,
    content = content,
  )
}

private fun List<NamedNavArgument>.appendScreenName(
  label: String? = null,
): List<NamedNavArgument> = when {
  label != null -> {
    this + navArgument(SCREEN_NAME_LABEL_ARG) { defaultValue = label }
  }

  else -> this
}

private const val SCREEN_NAME_LABEL_ARG = "screen_name"

val NavBackStackEntry.screenName: String
  get() = arguments?.getString(SCREEN_NAME_LABEL_ARG) ?: "Composable"
