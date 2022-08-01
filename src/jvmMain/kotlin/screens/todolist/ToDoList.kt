package screens.todolist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import navcontroller.NavController

@Composable
fun ToDoList(navController: NavController) {
    Text(navController.currentScreen.value)
}