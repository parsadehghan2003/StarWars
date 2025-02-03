package com.parsadehghan.designsystem.theme

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun PreviewStarWarsTheme(content: @Composable () -> Unit) {
  StarWarsTheme {
    Surface {
      content()
    }
  }
}
