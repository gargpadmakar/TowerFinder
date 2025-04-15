package com.padmakar.jetpackcomposeintroduction.screen.register.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.R
import com.padmakar.jetpackcomposeintroduction.components.OTP_VIEW_TYPE_BOX
import com.padmakar.jetpackcomposeintroduction.components.OtpView
import com.padmakar.jetpackcomposeintroduction.screen.register.viewmodel.RegisterOTPViewModel


@Composable
fun RegisterOTPScreen(navController: NavController, email: String, phone: String,viewModel: RegisterOTPViewModel = viewModel()) {
    val context= LocalContext.current
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
                        text = "OTP Verification",
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
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center){
                        Text(
                            text = "We will verify the OTP sent to\n$phone",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.head_sub_text_color),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OtpView(
                        otpText = uiState.mobileOtp,
                        otpCount = 4, // Set OTP length
                        type = OTP_VIEW_TYPE_BOX, // or OTP_VIEW_TYPE_UNDERLINE
                        onOtpTextChange = { uiState.mobileOtp = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center){
                        Text(
                            text = "We will verify the OTP sent to\n$email",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.head_sub_text_color),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OtpView(
                        otpText = uiState.emailOtp,
                        otpCount = 4, // Set OTP length
                        type = OTP_VIEW_TYPE_BOX, // or OTP_VIEW_TYPE_UNDERLINE
                        onOtpTextChange = { uiState.emailOtp = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        val clickable = !uiState.isTimerRunning

                        Text(
                            text = buildAnnotatedString {
                                if (uiState.isTimerRunning) {
                                    append("Resend ")
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("${uiState.timeLeft} sec")
                                    }
                                } else {
                                    withStyle(style = SpanStyle(color = Color.Black)) {
                                        append("Resend OTP")
                                    }
                                }
                            },
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .clickable(
                                    enabled = clickable,
                                    onClick = {
                                        uiState.timeLeft = 120
                                        uiState.isTimerRunning = true
                                        Toast.makeText(context, "OTP Resent", Toast.LENGTH_SHORT).show()
                                        // 🔁 Trigger your resend OTP API here
                                    }
                                )
                        )
                    }


                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = {
                            Toast.makeText(context, "Recover Password Clicked", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.colorPrimary)
                        ), shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(text = "Verify")
                    }


                }

            }
        }
    }
}



@Preview
@Composable
fun RegisterOTPScreenPreview() {
    val navController = rememberNavController()
    RegisterOTPScreen(navController,"","")
}