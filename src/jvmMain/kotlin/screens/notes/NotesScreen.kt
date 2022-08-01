package screens.notes

import Screens
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import navcontroller.NavController

@Composable
fun NotesScreen(navController: NavController) {
    Column {
        Text(navController.currentScreen.value)
        Button({navController.navigate(Screens.ToDoList.name)}) {
            Text("Navigate")
        }
    }
}