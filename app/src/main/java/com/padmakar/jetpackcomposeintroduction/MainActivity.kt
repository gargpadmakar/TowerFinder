package com.padmakar.jetpackcomposeintroduction

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContent {
             UserCard()
             //Title()
         }

    }
}

@Composable
fun Title(){
    val context= LocalContext.current
    Box(modifier=Modifier.fillMaxSize(),
              contentAlignment=Alignment.Center){
        Text(text = stringResource(id=R.string.app_name),
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                Toast.makeText(context, "Title Clicked",Toast.LENGTH_SHORT).show()
            }
        )
    }

}

@Composable
fun UserCard(){
    val context= LocalContext.current
    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color.White)
          ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            // 🔹 Top Banner Image
            Image(
                painter = painterResource(id = R.drawable.ic_demo),
                contentDescription = "Banner Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp),
                contentScale = ContentScale.Crop
            )
            // 🔹 User Info Row
            Row (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
                .border(width = 1.dp, color = Color.Gray)
                .padding(12.dp)){
                Image(painter = painterResource(id=R.drawable.myprofile),
                    contentDescription = "User Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(120.dp).clip(CircleShape)
                )
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(text= stringResource(id = R.string.developer_name),
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Button(onClick = {
                        // perform Button Click
                        Toast.makeText(context, "Title Clicked",Toast.LENGTH_SHORT).show()

                    }) {
                        Text(text = "View Profile")
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultMainPreview(){
    Surface(Modifier.fillMaxSize()){
        UserCard()
       // Title()
    }

}


