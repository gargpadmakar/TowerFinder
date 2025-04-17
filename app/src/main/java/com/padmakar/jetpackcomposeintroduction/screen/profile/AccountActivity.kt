package com.padmakar.jetpackcomposeintroduction.screen.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.graph.AuthNavGraph
import com.padmakar.jetpackcomposeintroduction.graph.ProfileNavGraph

class AccountActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfileScreen()
        }

    }
}

@Composable
fun ProfileScreen(){
  val navController= rememberNavController()
  ProfileNavGraph(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen()

}