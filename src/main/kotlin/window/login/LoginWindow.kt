package window.login

import OICQ
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.IntSize
import base.baseWindow
import ext.*
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import widget.alertDialog
import window.main.mainWindow

//class LoginWindow : BaseWindow(
//    title = "OICQ - 登录",
//    size = IntSize(530, 370)
//) {
//
//    /**
//     * 绘制界面
//     */
//    override fun setContent(): @Composable () -> Unit = {
//        // 数据驱动
//        var username by remember { mutableStateOf("2824720250") }
//        var password by savedInstanceState { "Fuckyou012." }
//
//        var state by savedInstanceState { false }
//
//        // 开始绘制
//        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            TextField(value = username, onValueChange = {
//                username = it
//            }, singleLine = true, backgroundColor = Color.White)
//            TextField(value = password, onValueChange = {
//                password = it
//            }, singleLine = true, backgroundColor = Color.White, visualTransformation = PasswordVisualTransformation())
//
//            Button(onClick = {
//                OICQ = BotFactory.newBot(username.toLong(), password) {
//                    fileBasedDeviceInfo()
//                }
//                launch {
//                    try {
//                        OICQ.login()
//                    } catch (e: Exception) {
//                        println("登录失败，${e.message}")
//                        return@launch
//                    }
//
//                    state = true
//                }
//            }) {
//                Text(text = "登录")
//          }
//        }
//
//        if (state) {
//            AlertDialog(onDismissRequest = {}, confirmButton = {}, title = {
//                Text(text = "OICQ")
//            }, text = {
//                Text(text = "登录成功！${OICQ.nick}")
//            })
//        }
//    }
//
//    /**
//     * 登录
//     */
//    private fun login(username: String, password: String) {
//    }
//
//}

data class LoginState(
    var finish: Boolean = false,
    var success: Boolean = false,
    var msg: String = ""
)

/**
 * 登录窗口
 */
fun loginWindow() = baseWindow(
    title = "OICQ - 登录",
    size = IntSize(530, 370)
) {
    // 数据驱动
    var username by remember { mutableStateOf("2824720250") }
    var password by savedInstanceState { "Fuckyou012." }
    var loginState by savedInstanceState { LoginState() }

    // 开始绘制
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = username, onValueChange = {
            username = it
        }, singleLine = true, backgroundColor = Color.White)
        TextField(value = password, onValueChange = {
            password = it
        }, singleLine = true, backgroundColor = Color.White, visualTransformation = PasswordVisualTransformation())

        Button(onClick = {
            login(username, password) { success, msg ->
                if (success) {
                    disposeWindow()
                    mainWindow()
                } else {
                    loginState = LoginState(true, success, msg)
                }
            }
        }) {
            Text(text = "登录")
        }
    }

    alertDialog(loginState.finish && !loginState.success, content = "登录失败！${loginState.msg}")
}

/**
 * 登录
 */
private fun login(username: String, password: String, callback: (success: Boolean, msg: String) -> Unit) {
    OICQ = BotFactory.newBot(username.toLong(), password) {
        fileBasedDeviceInfo()
    }

    launch {
        try {
            OICQ.login()
            callback(true, "")
        } catch (e: Exception) {
            callback(false, e.message?:"")
        }
    }
}