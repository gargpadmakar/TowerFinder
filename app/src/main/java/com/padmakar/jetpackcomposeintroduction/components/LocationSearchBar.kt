package com.padmakar.jetpackcomposeintroduction.components

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearClick: () -> Unit,
    onSearchClick: () -> Unit,
    onRadiusChange: (Int) -> Unit,
    radiusKm: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .height(50.dp), // Set fixed height to 50dp
            verticalAlignment = Alignment.CenterVertically
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(horizontal = 8.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
                .height(48.dp)
                .padding(horizontal = 8.dp), // Inside padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading Icon (Search Icon)
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp) // Adjust size
            )

            Spacer(modifier = Modifier.width(4.dp)) // Small space between icon and placeholder

            // TextField Placeholder + Input
            Box(modifier = Modifier.weight(1f)) {
                if (query.isEmpty()) {
                    Text(
                        text = "Search",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                // BasicTextField for user input
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChanged,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Trailing Icon (Clear Button)
            if (query.isNotBlank()) {
                IconButton(
                    onClick = onClearClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }




        // Radius Control Section (+ and - buttons) with bordered background
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(width = 120.dp, height = 50.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp)) // Background for search box
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)) // Border for search box
                .padding(horizontal = 10.dp)
        ) {
            IconButton(
                onClick = { onRadiusChange(radiusKm - 1) },
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Black, shape = CircleShape)
                    .padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Decrease Radius",
                    tint = Color.White
                )
            }

            Text(
                text = "$radiusKm Km",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            IconButton(
                onClick = { onRadiusChange(radiusKm + 1) },
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Black, shape = CircleShape)
                    .padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase Radius",
                    tint = Color.White
                )
            }
        }



        Spacer(modifier = Modifier.width(8.dp))

        // Send Button Section (Search Button) at the far right
        IconButton(
            onClick = onSearchClick,
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFFFB545), shape = CircleShape)
        ) {
            Icon(Icons.Default.Send, contentDescription = "Search", tint = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationSearchBar() {
    var searchQuery by remember { mutableStateOf("Gurgaon") }
    var radius by remember { mutableStateOf(2) }

    LocationSearchBar(
        query = searchQuery,
        onQueryChanged = { searchQuery = it },
        onClearClick = { searchQuery = "" },
        onSearchClick = {
            // 🔍 Trigger search using searchQuery and radius
        },
        onRadiusChange = { newRadius -> radius = newRadius.coerceIn(1, 50) }, // Optional: limit radius range
        radiusKm = radius
    )
}






