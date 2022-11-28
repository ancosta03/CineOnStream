package com.example.streamcine.ui.screens

import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(windowsSize: WindowSizeClass, modifier: Modifier = Modifier) {

    Text(text = "MAIN SCREEN")

    /* val navController = rememberNavController()

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
     }*/
}

// Banderole pour faire payer l'utilisateur