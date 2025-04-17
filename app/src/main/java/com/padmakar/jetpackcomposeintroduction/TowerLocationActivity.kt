package com.padmakar.jetpackcomposeintroduction


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.padmakar.jetpackcomposeintroduction.components.CustomAppBar
import com.padmakar.jetpackcomposeintroduction.components.LocationSearchBar
import com.padmakar.jetpackcomposeintroduction.screen.profile.AccountActivity


class TowerLocationActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapScreen()
        }
    }
}

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val currentLocation = LatLng(28.4164, 77.0932)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    var isSatelliteView by remember { mutableStateOf(false) }

    // Update map type based on switch
    properties = properties.copy(
        mapType = if (isSatelliteView) MapType.SATELLITE else MapType.NORMAL
    )

    Column(modifier = Modifier.fillMaxSize()) {

        // Top AppBar
        CustomAppBar(
            title = "Cell ID",
            onBackClick = { /* Handle back */ },
            onProfileClick = { /* Handle profile */
                val intent = Intent(context, AccountActivity::class.java) // Create an intent for AccountActivity
                context.startActivity(intent) // Start the activity using the context
            },
            switchChecked = isSatelliteView,
            onSwitchChanged = { isSatelliteView = it }
        )

        // Box to layer map + overlay view
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {

            // Google Map
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            ) {
                Marker(
                    state = MarkerState(position = currentLocation),
                    title = "Rajesh Pilot Gurjar Chowk"
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                // Search Bar Location AppBar
                var searchQuery by remember { mutableStateOf("Gurgaon") }
                var radius by remember { mutableStateOf(1) }
                LocationSearchBar(
                    query = searchQuery,
                    onQueryChanged = { searchQuery = it },
                    onClearClick = { searchQuery = "" },
                    onSearchClick = {
                        // 🔍 Trigger search using searchQuery and radius
                    },
                    onRadiusChange = { newRadius ->
                        radius = newRadius.coerceIn(1, 50) // Optional: limit radius range
                    },
                    radiusKm = radius
                )
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    MapScreen()
}

