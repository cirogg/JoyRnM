package com.cirogg.joyrnm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.cirogg.joyrnm.presentation.list.CharacterListScreen
import com.cirogg.joyrnm.presentation.detail.CharacterDetailScreen
import kotlinx.serialization.Serializable

@Composable
fun CharacterNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any = CharacterListRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<CharacterListRoute> {
            CharacterListScreen(
                onCharacterClick = { id ->
                    navController.navigate(CharacterDetailRoute(id))
                }
            )
        }

        composable<CharacterDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CharacterDetailRoute>()
            CharacterDetailScreen(
                characterId = route.characterId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Serializable
object CharacterListRoute

@Serializable
data class CharacterDetailRoute(val characterId: Long)