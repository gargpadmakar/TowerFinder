package com.padmakar.jetpackcomposeintroduction.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.graph.AuthNavGraph

class AuthActivity:ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthScreen()
        }

    }
}

@Composable
fun AuthScreen(){
    val navController = rememberNavController()
    AuthNavGraph(navController = navController)
}

@Preview
@Composable
fun AuthScreenPreview(){
    AuthScreen()
}