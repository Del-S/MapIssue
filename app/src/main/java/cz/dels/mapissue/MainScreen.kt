package cz.dels.mapissue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import kotlinx.serialization.Serializable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import cz.dels.mapissue.ui.HomeScreen
import cz.dels.mapissue.ui.MapScreen

/**
 * TODO CLASS_DESCRIPTION
 *
 * @author [David Sucharda](mailto:david.sucharda@cleevio.com)
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun MainScreen(
    navController: NavHostController,
) {
    Scaffold(
        modifier = Modifier.semantics { testTagsAsResourceId = true },
        bottomBar = {
            BottomNavigationContent(
                navController = navController,
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                MainScreenNavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp),
                    navController = navController,
                )
            }
        })
}

@Composable
private fun BottomNavigationContent(
    navController: NavHostController,
) {
    val bottomBackStackEntry by navController.currentBackStackEntryAsState()
    Column(modifier = Modifier.background(Color.Black)) {
        // Note: must be thickness 2.dp and height 1.dp else there are few black pixels under it.
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            thickness = 2.dp,
            color = Color.Gray
        )
        NavigationBar(
            modifier = Modifier,
            containerColor = Color.White,
            contentColor = Color.Black,
            tonalElevation = 0.dp
        ) {
            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                listOf(Home, Map).forEach {
                    MainBottomNavigationItem(
                        navController = navController,
                        target = it
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomNavigationItem(
    navController: NavController,
    target: BottomDestination,
) {
    val isSelected = false
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_black_24dp),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        label = {
            Text(
                textAlign = TextAlign.Center,
                text = target.javaClass.simpleName,
                style = MaterialTheme.typography.labelMedium.copy(lineBreak = LineBreak.Heading),
            )
        },
        selected = isSelected,
        onClick = {
            navController.navigate(target) {
                popUpTo(Home) {
                    this.saveState = true
                }
                restoreState = true
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun MainScreenNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier.semantics { testTagsAsResourceId = true },
    ) {
        composable<Home> { HomeScreen() }
        composable<Map> { MapScreen() }
    }
}

interface BottomDestination
@Serializable
object Home : BottomDestination
@Serializable
object Map : BottomDestination