package com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.R
import com.padmakar.jetpackcomposeintroduction.navigation.navigateToForgotPasswordOTP
import com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(navController: NavController,viewModel: ForgotPasswordViewModel= viewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_white_24dp),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        onClick = { navController.popBackStack() },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White)
                    )
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(50.dp))


            // 🔥 This Box contains the form + background image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                    .background(Color.White)
            ) {

                // Form fields over image
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Forgot Password",
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
                            .width(150.dp)
                            .background(color = colorResource(id = R.color.colorPrimary))
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


                        Text(
                            text = "Enter your email or mobile number to receive a one-time password (OTP) via text message or email."
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "*Your password must be a minimum of 8 characters long."
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = uiState.emailOrMobile,
                            onValueChange = { viewModel.onEmailOrMobileChange(it) },
                            singleLine = true,
                            label = { Text("Email or Mobile") },
                            isError = uiState.emailError != null,
                            supportingText = {
                                if (uiState.emailError != null) {
                                    Text(
                                        text = uiState.emailError!!,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = Color.Red
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = uiState.newPassword,
                            onValueChange = { viewModel.onNewPasswordChange(it) },
                            label = { Text("New Password") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = Color.Red
                            ),
                            isError = uiState.passwordError != null,
                            supportingText = {
                                if (uiState.passwordError != null) {
                                    Text(
                                        text = uiState.passwordError!!,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            visualTransformation = if (uiState.passwordNewVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image =
                                    if (uiState.passwordNewVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                val description =
                                    if (uiState.passwordNewVisible) "Hide password" else "Show password"

                                IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                    Icon(imageVector = image, contentDescription = description)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = uiState.verifyPassword,
                            onValueChange = { viewModel.onVerifyPasswordChange(it) },
                            label = { Text("Confirm Password") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = Color.Red
                            ),
                            isError = uiState.confirmPasswordError != null,
                            supportingText = {
                                if (uiState.confirmPasswordError != null) {
                                    Text(
                                        text = uiState.confirmPasswordError!!,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            visualTransformation = if (uiState.passwordVerifyVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image =
                                    if (uiState.passwordVerifyVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                val description =
                                    if (uiState.passwordVerifyVisible) "Hide password" else "Show password"

                                IconButton(onClick = {
                                    viewModel.toggleVerifyPasswordVisibility()
                                }) {
                                    Icon(imageVector = image, contentDescription = description)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(
                            onClick = {
                                if (viewModel.validateForm()) {
                                    navigateToForgotPasswordOTP(navController,uiState.emailOrMobile,uiState.inputType)
                                    Toast.makeText(
                                        context,
                                        "Recover Password Clicked",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.colorPrimary)
                            ), shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(text = "Recover Password")
                        }

                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    val navController = rememberNavController()
    ForgotPasswordScreen(navController)
}




