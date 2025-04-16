package com.padmakar.jetpackcomposeintroduction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.padmakar.jetpackcomposeintroduction.R


@Composable
fun CustomAppBar(
    title: String = "Cell Id",
    onBackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onLocationClick: () -> Unit = {},
    switchChecked: Boolean = false,
    onSwitchChanged: (Boolean) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        color = Color.Black,
        shadowElevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Back button
            Image(painter = painterResource(id= R.drawable.ic_logo),
                contentDescription = "User Image",
                modifier = Modifier.size(35.dp).clickable { onBackClick() }
            )

            // Title
            Text(
                text = title,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                fontWeight = FontWeight.SemiBold
            )

            // Map Mode Toggle
            Text(text = "Map", fontSize = 14.sp, color = Color.White)
            Switch(
                checked = switchChecked,
                onCheckedChange = { onSwitchChanged(it) },
                modifier = Modifier.padding(horizontal = 4.dp)
            )


            // Profile Icon
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onProfileClick() }
                    .padding(start = 6.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CustomAppBarPreview() {
    MaterialTheme {
        CustomAppBar(
            title = "Custom App Bar",
            onBackClick = {},
            onProfileClick = {},
            onLocationClick = {},
            switchChecked = true,
            onSwitchChanged = {}
        )
    }
}

