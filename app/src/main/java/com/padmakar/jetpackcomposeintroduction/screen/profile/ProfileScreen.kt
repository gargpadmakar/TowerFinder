package com.padmakar.jetpackcomposeintroduction.screen.profile

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.padmakar.jetpackcomposeintroduction.R


@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current

    // Profile options content
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
                        onClick = {  (context as? Activity)?.finish() },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = Color.White)
                    )
            )
            // Title
            Text(
                text = "Profile",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(60.dp))
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
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    // 🔹 User Info Row
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()){
                        Image(painter = painterResource(id=R.drawable.myprofile),
                            contentDescription = "User Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(65.dp).clip(CircleShape)
                        )
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text= "James Anand Singh",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text= "+91-9918130073",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            HorizontalDivider(
                                modifier = Modifier.padding(end = 10.dp),
                                thickness = 1.dp,
                                color = Color.LightGray
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically // ⬅️ Align everything vertically
                    ) {
                        // Profile Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        // Account Text Info (vertically centered)
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart // ⬅️ Align text start and vertical center
                        ) {
                            Text(
                                text = "Account",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Right Arrow Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_details),
                            contentDescription = "Account Details",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 60.dp, end = 20.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically // ⬅️ Align everything vertically
                    ) {
                        // Profile Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        // Account Text Info (vertically centered)
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart // ⬅️ Align text start and vertical center
                        ) {
                            Text(
                                text = "My recent searches",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Right Arrow Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_details),
                            contentDescription = "Account Details",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 60.dp, end = 20.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically // ⬅️ Align everything vertically
                    ) {
                        // Profile Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        // Account Text Info (vertically centered)
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart // ⬅️ Align text start and vertical center
                        ) {
                            Text(
                                text = "Current plan:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Right Arrow Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_details),
                            contentDescription = "Account Details",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 60.dp, end = 20.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically // ⬅️ Align everything vertically
                    ) {
                        // Profile Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        // Account Text Info (vertically centered)
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart // ⬅️ Align text start and vertical center
                        ) {
                            Text(
                                text = "View tower reports",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Right Arrow Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_details),
                            contentDescription = "Account Details",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 60.dp, end = 20.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically // ⬅️ Align everything vertically
                    ) {
                        // Profile Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        // Account Text Info (vertically centered)
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart // ⬅️ Align text start and vertical center
                        ) {
                            Text(
                                text = "Privacy policy",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Right Arrow Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_details),
                            contentDescription = "Account Details",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 60.dp, end = 20.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically // ⬅️ Align everything vertically
                    ) {
                        // Profile Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        // Account Text Info (vertically centered)
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart // ⬅️ Align text start and vertical center
                        ) {
                            Text(
                                text = "Terms & conditions",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Right Arrow Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_details),
                            contentDescription = "Account Details",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 60.dp, end = 20.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.Center

                // ⬅️ Align text start and vertical center
                ) {
                    Text(
                        text = "App Version 1.0.0",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileOptionsPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}