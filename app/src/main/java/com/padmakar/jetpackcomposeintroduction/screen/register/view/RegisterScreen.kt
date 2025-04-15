package com.padmakar.jetpackcomposeintroduction.screen.register.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.R
import com.padmakar.jetpackcomposeintroduction.navigation.navigateToLogin
import com.padmakar.jetpackcomposeintroduction.navigation.navigateToRegisterOTP
import com.padmakar.jetpackcomposeintroduction.screen.register.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel= viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorPrimary))
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
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Create Account",
                    color = colorResource(id = R.color.head_text_color),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                        .width(130.dp)
                        .background(color = colorResource(id = R.color.colorPrimary))
                )
                Spacer(modifier = Modifier.height(40.dp))
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


                // Form fields with error messages
                OutlinedTextField(
                    value = uiState.fullName,
                    onValueChange = { viewModel.onFullNameChange(it) },
                    label = { Text("Full Name", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = uiState.fullNameError != null,
                    supportingText = {
                        if (uiState.fullNameError != null) {
                            Text(
                                text = uiState.fullNameError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text("Email", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.emailError != null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    ),
                    supportingText = {
                        if (uiState.emailError != null) {
                            Text(
                                text = uiState.emailError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.mobileNumber,
                    onValueChange = {
                        // Accept only digits and limit to 10 digits
                        if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                            viewModel.onMobileNumberChange(it)
                        }
                    },
                    label = { Text("Mobile Number", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.mobileNumberError != null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    supportingText = {
                        if (uiState.mobileNumberError != null) {
                            Text(
                                text = uiState.mobileNumberError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    ),
                    visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (uiState.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (uiState.passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                            Icon(imageVector = image, contentDescription = description)
                        }
                    },
                    isError = uiState.passwordError != null,
                    supportingText = {
                        if (uiState.passwordError != null) {
                            Text(
                                text = uiState.passwordError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.organization,
                    onValueChange = { viewModel.onOrganizationChange(it) },
                    label = { Text("Organization", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.organizationError != null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    supportingText = {
                        if (uiState.organizationError != null) {
                            Text(
                                text = uiState.organizationError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.designation,
                    onValueChange = { viewModel.onDesignationChange(it) },
                    label = { Text("Designation", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.designationError != null,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        errorBorderColor = Color.Red
                    ),
                    supportingText = {
                        if (uiState.designationError != null) {
                            Text(
                                text = uiState.designationError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (viewModel.validateForm()) {
                            // Navigate to the OTP screen only if form is valid
                            navigateToRegisterOTP(navController, uiState.email, uiState.mobileNumber)
                            viewModel.clearInputs() // Reset the form after successful registration
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.colorPrimary)
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Continue")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        color = colorResource(id = R.color.head_sub_text_color)
                    )
                    TextButton(
                        onClick = { navigateToLogin(navController) },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.head_text_color)
                        )
                    }
                }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}
