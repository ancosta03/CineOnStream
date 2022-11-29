package com.example.streamcine.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.streamcine.R


sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon : ImageVector) {
    object Authentification : Screen("Authentification", R.string.authentification, Icons.Filled.Person)
    object MovieDetails : Screen("movie_details", R.string.movie_details, Icons.Filled.List)
    object Search : Screen("search", R.string.search, Icons.Filled.Search)
}

val MainScreens = listOf(Screen.Authentification,
    Screen.MovieDetails,
    Screen.Search)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(@StringRes title: Int,
           canNavigateBack: Boolean,
           modifier: Modifier = Modifier,
           navigateBack: ()->Unit) {
    TopAppBar(
        title = {Text(stringResource(id = title))},
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
        )
    Text("Text")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(windowsSize: WindowSizeClass, modifier: Modifier = Modifier){
    val navController = rememberNavController()

    when(windowsSize.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            CompactWidthNavigation(modifier, navController = navController)
        }
        WindowWidthSizeClass.Medium -> {
            CompactWidthNavigation(navController = navController)
        }
        WindowWidthSizeClass.Expanded -> {
            ExpandedWidthNavigation(modifier, navController = navController)
        }
        else -> {
            CompactWidthNavigation(navController = navController)
        }
    }
}

@Composable
fun ExpandedWidthNavigation(modifier: Modifier, navController: NavHostController) {

}

fun rememberNavController(): NavHostController {

}

@Composable
fun CompactWidthNavigation(modifier : Modifier = Modifier,
                           navController : NavHostController)
{
    var currentScreenTitle by remember {
        mutableStateOf(Screen.Profile.resourceId)
    }

    var canNavigateBack by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopBar(title = currentScreenTitle, canNavigateBack = canNavigateBack, modifier) {
                navController.navigateUp()
            }
        },
        bottomBar = {
            BottomBar(navController, modifier) {
                currentScreenTitle = it.resourceId
            }
        }) {
            innerPadding -> AppBody(innerPadding = innerPadding,
        navController = navController,
        modifier = modifier,
        onTitleChanged = {
            currentScreenTitle = it;
        },
        onCanNavigateBackChanged = {
            canNavigateBack = it
        })
    }
}

@Composable
fun AppBody(innerPadding: PaddingValues, navController: NavHostController, modifier: Modifier, onTitleChanged: () -> Unit, onCanNavigateBackChanged: () -> Unit) {

}

@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier, onNavigate: (Screen)->Unit) {
    BottomNavigation(modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        MainScreens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    onNavigate(screen)
                }
            )
        }
    }
}
