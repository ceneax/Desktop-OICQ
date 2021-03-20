package widget

import androidx.compose.desktop.AppManager
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.window.DialogProperties
import ext.closeWindow

@Composable
fun alertDialog(
    show: Boolean = true,
    confirmText: String? = "确定",
    dismissText: String? = null,
    title: String? = null,
    content: String? = null,
) {
    if (!show) {
        return
    }

    AlertDialog(onDismissRequest = {}, confirmButton = {
        confirmText?.let {
            Button(onClick = {
                closeWindow()
            }) {
                Text(text = it)
            }
        }
    }, modifier = Modifier, dismissButton = {
        dismissText?.let {
            Button(onClick = {
                closeWindow()
            }) {
                Text(text = it)
            }
        }
    }, title = {
        title?.let {
            Text(text = it)
        }
    }, text = {
        content?.let {
            Text(text = it)
        }
    }, shape = MaterialTheme.shapes.medium, backgroundColor = MaterialTheme.colors.surface, contentColor = MaterialTheme.colors.surface, properties = null)
}