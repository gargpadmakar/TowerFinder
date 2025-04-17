package com.padmakar.jetpackcomposeintroduction.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.padmakar.jetpackcomposeintroduction.navigation.Routes
import com.padmakar.jetpackcomposeintroduction.screen.profile.ProfileScreen

@Composable
fun ProfileNavGraph(navController: NavHostController){
    NavHost(navController= navController,startDestination = Routes.Profile.route) {
        composable(Routes.Profile.route) { ProfileScreen(navController) }

    }

}
