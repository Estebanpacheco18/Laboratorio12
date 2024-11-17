package com.example.laboratory12

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline

@Composable
fun MapScreen() {
    val arequipaLocation = LatLng(-16.4040102, -71.559611) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(arequipaLocation, 12f)
    }

    val locations = listOf(
        LatLng(-16.433415, -71.5442652), // JLByR
        LatLng(-16.4205151, -71.4945209), // Paucarpata
        LatLng(-16.3524187, -71.5675994) // Zamacola
    )

    val polyline1 = listOf(
        LatLng(-16.4040102, -71.559611),
        LatLng(-16.433415, -71.5442652),
        LatLng(-16.4205151, -71.4945209)
    )

    val polyline2 = listOf(
        LatLng(-16.3524187, -71.5675994),
        LatLng(-16.398866, -71.536961),
        LatLng(-16.432292, -71.509145)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcador en Arequipa, Perú con icono azul
            Marker(
                state = rememberMarkerState(position = arequipaLocation),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE), // Icono azul
                title = "Arequipa, Perú"
            )
            // Añadir marcador en Arequipa, Perú con icono personalizado
            Marker(
                state = rememberMarkerState(position = arequipaLocation),
                icon = BitmapDescriptorFactory.fromResource(R.drawable.mountain), // Icono personalizado
                title = "Arequipa, Perú"
            )
            // Añadir varios marcadores
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    title = "Ubicación",
                    snippet = "Punto de interés"
                )
            }
            // Dibujar polilíneas
            Polyline(
                points = polyline1,
                color = Color.Red,
                width = 5f
            )
            Polyline(
                points = polyline2,
                color = Color.Blue,
                width = 5f
            )
        }
    }

    // Controlar la cámara programáticamente
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Mover a Yura
            durationMs = 3000
        )
    }
}