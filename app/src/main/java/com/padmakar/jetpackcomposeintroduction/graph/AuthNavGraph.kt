package com.padmakar.jetpackcomposeintroduction.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.padmakar.jetpackcomposeintroduction.navigation.Routes
import com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.view.ForgotPasswordOTPScreen
import com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.view.ForgotPasswordScreen
import com.padmakar.jetpackcomposeintroduction.screen.login.view.LoginOTPScreen
import com.padmakar.jetpackcomposeintroduction.screen.login.view.LoginScreen
import com.padmakar.jetpackcomposeintroduction.screen.register.view.RegisterOTPScreen
import com.padmakar.jetpackcomposeintroduction.screen.register.view.RegisterScreen

// graph/AuthNavGraph.kt
@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) { LoginScreen(navController) }
        composable(Routes.Register.route) { RegisterScreen(navController) }
        composable(Routes.ForgotPassword.route) { ForgotPasswordScreen(navController) }

        composable(
            route = Routes.RegisterOTP.route,
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            RegisterOTPScreen(navController, email, phone)
        }

        composable(
            route = Routes.LoginOTP.route,
            arguments = listOf(
                navArgument("otpParam") { type = NavType.StringType },
                navArgument("inputType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val otpParam = backStackEntry.arguments?.getString("otpParam") ?: ""
            val inputType = backStackEntry.arguments?.getString("inputType") ?: ""
            LoginOTPScreen(navController, otpParam, inputType)

        }

        composable(
            route = Routes.ForgotPasswordOTP.route,
            arguments = listOf(
                navArgument("otpParam") { type = NavType.StringType },
                navArgument("inputType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val otpParam = backStackEntry.arguments?.getString("otpParam") ?: ""
            val inputType = backStackEntry.arguments?.getString("inputType") ?: ""
            ForgotPasswordOTPScreen(navController, otpParam, inputType)

        }
    }
}
