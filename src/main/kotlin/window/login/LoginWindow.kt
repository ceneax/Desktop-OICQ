package window.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import base.BaseWindow

class LoginWindow : BaseWindow(
    title = "OICQ - 登录",
    size = IntSize(530, 370)
) {

    override fun setContent(): @Composable () -> Unit = {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = "", onValueChange = {}, singleLine = true, backgroundColor = Color.White)
            TextField(value = "", onValueChange = {}, singleLine = true, backgroundColor = Color.White)
        }
    }

}