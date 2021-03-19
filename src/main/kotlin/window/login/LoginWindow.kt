package window.login

import OICQ
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import base.BaseWindow
import ext.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory

class LoginWindow : BaseWindow(
    title = "OICQ - 登录",
    size = IntSize(530, 370)
) {

    private var state by savedInstanceState { false }

    /**
     * 绘制界面
     */
    override fun setContent(): @Composable () -> Unit = {
        // 数据驱动
        var username by remember { mutableStateOf("") }
        var password by savedInstanceState { "" }

        // 开始绘制
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = username, onValueChange = {
                username = it
            }, singleLine = true, backgroundColor = Color.White)
            TextField(value = password, onValueChange = {
                password = it
            }, singleLine = true, backgroundColor = Color.White, visualTransformation = PasswordVisualTransformation())

            Button(onClick = {
                login(username, password)
            }) {
                Text(text = "登录")
          }
        }

        if (state) {
            AlertDialog(onDismissRequest = {}, confirmButton = {}, title = {
                Text(text = "OICQ")
            }, text = {
                Text(text = "登录成功！${OICQ.nick}")
            })
        }
    }

    /**
     * 登录
     */
    @Composable
    private fun login(username: String, password: String) {
        OICQ = BotFactory.newBot(username.toLong(), password) {
            fileBasedDeviceInfo()
        }
        launch {
            try {
                OICQ.login()
            } catch (e: Exception) {
                println("登录失败，${e.message}")
                return@launch
            }

            state = true
        }
    }

}