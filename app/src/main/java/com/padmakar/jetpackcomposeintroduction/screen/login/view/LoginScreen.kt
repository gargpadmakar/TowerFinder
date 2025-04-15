package com.padmakar.jetpackcomposeintroduction.screen.login.view

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.R
import com.padmakar.jetpackcomposeintroduction.components.LoadingDialog
import com.padmakar.jetpackcomposeintroduction.components.ShowLoadingUI
import com.padmakar.jetpackcomposeintroduction.core.BaseEventHandler
import com.padmakar.jetpackcomposeintroduction.navigation.navigateToForgotPassword
import com.padmakar.jetpackcomposeintroduction.navigation.navigateToLoginOTP
import com.padmakar.jetpackcomposeintroduction.navigation.navigateToRegister
import com.padmakar.jetpackcomposeintroduction.screen.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val context= LocalContext.current
    val uiState by viewModel.uiState.collectAsState()


    // Back press interception
    BackHandler {
        (context as?Activity)?.finish()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(40.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.welcome_to_app),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Form Section
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                    .background(Color.White)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Login",
                        color = colorResource(id = R.color.head_text_color),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                            .width(60.dp)
                            .background(color = colorResource(id = R.color.colorPrimary))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        EmailOrPhoneInput(
                            value = uiState.emailOrPhone,
                            onValueChange = { viewModel.onEmailOrPhoneChange(it) },
                            error = uiState.emailOrPhoneError
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = uiState.password,
                            onValueChange = { viewModel.onPasswordChange(it) },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth(),
                            isError = uiState.passwordError != null,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = Color.Red
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            supportingText = {
                                if (uiState.passwordError != null) {
                                    Text(
                                        text = uiState.passwordError!!,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                    val image =
                                        if (uiState.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                    val description =
                                        if (uiState.passwordVisible) "Hide password" else "Show password"
                                    Icon(imageVector = image, contentDescription = description)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = {
                                navigateToForgotPassword(navController)
                                viewModel.clearInputs()
                            }) {
                                Text("Forgot Password?", color = colorResource(id = R.color.red))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                if (viewModel.validateForm()) {
                                    viewModel.performLogin {
                                        navigateToLoginOTP(
                                            navController,
                                            uiState.emailOrPhone,
                                            uiState.inputType
                                        )
                                        viewModel.clearInputs()
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimary)),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("Continue")
                        }



                        Spacer(modifier = Modifier.height(24.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Or",
                                color = colorResource(id = R.color.head_sub_text_color)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Don't have an account?",
                                color = colorResource(id = R.color.head_sub_text_color)
                            )
                            TextButton(
                                onClick = { navigateToRegister(navController)
                                          viewModel.clearInputs()},
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = " Register",
                                    color = colorResource(id = R.color.head_text_color)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    // LoadingDialog should be added here to appear in the center
    ShowLoadingUI(isLoading = viewModel.isLoading.value)
}

@Preview
@Composable
fun LoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}