package com.padmakar.jetpackcomposeintroduction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun LoadingDialog() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(80.dp)
            .background(Color.Black.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp))
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.Center),
            color = Color.Yellow,
            strokeWidth = 3.dp
        )
        Text(
            text = "Please wait",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            color = Color.White,
            fontSize = 13.sp
        )
    }
}

@Composable
fun ShowLoadingUI(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the entire screen
                .background(Color.Black.copy(alpha = 0.4f)) // Semi-transparent background
                .wrapContentSize(Alignment.Center) // Ensure the loading dialog is centered
        ) {
            LoadingDialog() // Show the loading dialog, it's already centered inside the Box
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingDialogPreview() {
    LoadingDialog()
}

@Preview(showBackground = true)
@Composable
fun ShowLoadingUIPreview() {
    ShowLoadingUI(isLoading = true)
}
