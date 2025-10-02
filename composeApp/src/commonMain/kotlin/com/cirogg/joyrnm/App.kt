package com.cirogg.joyrnm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cirogg.joyrnm.presentation.navigation.CharacterNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    CharacterNavGraph(
        navController = navController,
        modifier = Modifier
    )
}