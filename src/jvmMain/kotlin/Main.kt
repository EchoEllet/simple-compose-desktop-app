// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import navcontroller.rememberNavController
import screens.notes.NotesScreen
import screens.todolist.ToDoList
import java.awt.Dimension

val screens = Screens.values().toList()

@Composable
@Preview
fun App(windowState: WindowState) {
    val windowSize = windowState.size
    val navController by rememberNavController(Screens.NotesScreen.name)
    val currentScreen by remember { navController.currentScreen }

    MaterialTheme(colors = darkColors()) {
        Surface(color = MaterialTheme.colors.background) {
            Row {
                ScreenContainer(
                    Modifier.fillMaxHeight().width((windowSize.width * 90f) / 100f),
                    navController
                )
                NavigationMenu(
                    Modifier.fillMaxHeight().width((windowSize.width * 10f) / 100f),
                    navController,
                    currentScreen
                )
            }
        }
    }
}

fun main() = application {
    val windowState = rememberWindowState()
    Window(onCloseRequest = ::exitApplication, state = windowState) {
        this.window.minimumSize = Dimension(800, 600)
        App(windowState)
    }
}

@Composable
fun ScreenContainer(modifier: Modifier = Modifier, navController: NavController) {
    Box(Modifier.then(modifier)) {
        NavigationHost(navController) {
            composable(Screens.NotesScreen.name) {
                NotesScreen(navController)
            }
            composable(Screens.ToDoList.name) {
                ToDoList(navController)
            }
        }.build()
    }
}

@Composable
fun NavigationMenu(modifier: Modifier = Modifier, navController: NavController, currentScreen: String) {
    Card(Modifier.then(modifier), shape = RoundedCornerShape(
        topStart = 15.dp,
        bottomStart = 15.dp
    )) {
        NavigationRail {
            screens.forEach {
                NavigationRailItem(
                    selected = it.name == currentScreen,
                    label = { Text(it.label) },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.label
                        )
                    },
                    onClick = { navController.navigate(it.name) },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

enum class Screens(
    val label: String,
    val icon: ImageVector
) {
    NotesScreen("Note", Icons.Filled.Home),
    ToDoList("Work", Icons.Filled.Add)
}
