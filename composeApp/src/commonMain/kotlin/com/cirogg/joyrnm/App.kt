package com.cirogg.joyrnm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cirogg.joyrnm.presentation.detail.CharacterDetailScreen
import com.cirogg.joyrnm.presentation.list.CharacterListScreen
import com.cirogg.joyrnm.presentation.navigation.CharacterDetailRoute
import com.cirogg.joyrnm.presentation.navigation.CharacterListRoute
import com.cirogg.joyrnm.presentation.navigation.CharacterNavGraph
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import joyrnm.composeapp.generated.resources.Res
import joyrnm.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    CharacterNavGraph(
        navController = navController,
        modifier = Modifier
    )
}