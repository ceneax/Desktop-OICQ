package window.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntSize
import base.baseWindow

fun mainWindow() = baseWindow(
    title = "OICQ",
    size = IntSize(450, 900)
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
//        Image(painter = pai)
        Column {
            Text("")
            Text("")
        }
    }
}