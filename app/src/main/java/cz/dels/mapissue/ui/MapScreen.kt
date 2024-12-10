package cz.dels.mapissue.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.maps.android.compose.DefaultMapProperties
import com.google.maps.android.compose.DefaultMapUiSettings
import com.google.maps.android.compose.GoogleMap

/**
 * TODO CLASS_DESCRIPTION
 *
 * @author [David Sucharda](mailto:david.sucharda@cleevio.com)
 */
@Composable
fun MapScreen() {
    GoogleMap(
        uiSettings = DefaultMapUiSettings.copy(zoomControlsEnabled = false),
        properties = DefaultMapProperties.copy(
            minZoomPreference = 1f,
            maxZoomPreference = 20f,
        )
    )
}