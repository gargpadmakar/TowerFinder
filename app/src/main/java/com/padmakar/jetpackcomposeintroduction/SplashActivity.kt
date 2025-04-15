package com.padmakar.jetpackcomposeintroduction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.padmakar.jetpackcomposeintroduction.screen.AuthActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashIntro()
        }

        //start a coroutine to redirect after 2 seconds
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
            finish()
        }

    }

}

@Composable
fun SplashIntro(){
    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color.Black),
        contentAlignment=Alignment.Center) {
         Column(horizontalAlignment = Alignment.CenterHorizontally ) {
             Image(painter = painterResource(id = R.drawable.ic_logo),
                 contentDescription = "App Logo",
                 alignment = Alignment.Center,
                 modifier = Modifier.size(120.dp))
              Spacer(modifier = Modifier.padding(8.dp))
              Text(text = stringResource(id = R.string.app_name), color = Color.White, fontSize = 20.sp, fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center)
         }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSplashPreview(){
    SplashIntro()
}