package com.example.bottomnavigationbarwithbadges

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bottomnavigationbarwithbadges.ui.theme.BottomNavigationBarWithBadgesTheme

data class BottomNavigationItem(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationBarWithBadgesTheme {
                // A surface container using the 'background' color from the theme
                val items = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Filled.Home,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "Chat",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Filled.Email,
                        hasNews = false,
                        badgeCount = 45
                    ),
                    BottomNavigationItem(
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Filled.Settings,
                        hasNews = true,
                    ),
                )
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                           NavigationBar {
                               items.forEachIndexed { index, item ->
                                   NavigationBarItem(
                                       selected = selectedItemIndex == index,
                                       onClick = {
                                                 selectedItemIndex = index
//                                           navController.navigate(item.title)
                                       },
                                       label = {
                                               Text(text = item.title)
                                       },
                                       icon = {
                                           BadgedBox(
                                               badge = {
                                                   if(item.badgeCount != null){
                                                       Badge{
                                                           Text(text = item.badgeCount.toString())
                                                       }
                                                   }else if(item.hasNews){
                                                       Badge()
                                                   }
                                               }
                                           ) {
                                               Icon(
                                                   imageVector = if(index == selectedItemIndex){
                                                       item.selectedIcon
                                                   }else{
                                                       item.unselectedIcon
                                                   },
                                                   contentDescription = item.title
                                               )
                                           }
                                       }
                                   )
                               }
                           }
                        }
                    ) {

                    }
                }
            }
        }
    }
}
