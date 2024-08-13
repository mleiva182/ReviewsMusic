package com.mleiva.reviewsmusic.presentation.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.presentation.navigation.HomeBottomBarNavGraph
import com.mleiva.reviewsmusic.presentation.navigation.HomeBottomBarScreen

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.home
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:25
 ***/
@Composable
fun HomeScreen(navHostController: NavHostController = rememberNavController()){

    Scaffold(
        bottomBar = { BottomBar(navHostController) }
    ) {
        HomeBottomBarNavGraph(it, navHostController = navHostController)
    }
}


@Composable
fun BottomBar(navHostController: NavHostController){

    val screens = listOf(
        HomeBottomBarScreen.Posts,
        HomeBottomBarScreen.MyPosts,
        HomeBottomBarScreen.Profile,
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentdException = navBackStackEntry?.destination
    val bottomBarDestination = screens.any() { it.route == currentdException?.route }

    if (bottomBarDestination){

        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentdException,
                    navHostController = navHostController
                )
            }
        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(screen.route){
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop == true
            }
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation icon"
            )
        })
}