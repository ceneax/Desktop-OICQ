package base

import androidx.compose.desktop.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.emptyContent
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import ext.getWindow
import java.awt.Color
import java.awt.image.BufferedImage

//abstract class BaseWindow(
//    title: String = "",
//    size: IntSize = IntSize(800, 600),
//    location: IntOffset = IntOffset.Zero,
//    centered: Boolean = true,
//    icon: BufferedImage? = null,
//    menuBar: MenuBar? = null,
//    undecorated: Boolean = false
//) {
//
//    private val mContent: @Composable () -> Unit
//    private val mEvents: WindowEvents = WindowEvents()
//
//    init {
//        mContent = setContent()
//
//        mEvents.onOpen = {
//            onOpen()
//        }
//        mEvents.onClose = {
//            onClose()
//        }
//        mEvents.onResize = {
//            onResize(it)
//        }
//
//        Window(title, size, location, centered, icon, menuBar, undecorated, mEvents, ::onDismissRequest, mContent)
//    }
//
//    abstract fun setContent(): @Composable () -> Unit
//
//    open fun onOpen() {}
//    open fun onClose() {}
//    open fun onDismissRequest() {}
//    open fun onResize(size: IntSize) {}
//
//}

fun baseWindow(
    title: String = "",
    size: IntSize = IntSize(800, 600),
    location: IntOffset = IntOffset.Zero,
    centered: Boolean = true,
    icon: BufferedImage? = null,
    menuBar: MenuBar? = null,
    undecorated: Boolean = true, // 隐藏窗口边框
    events: WindowEvents = WindowEvents(),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit = {}
) = Window(title, size, location, centered, icon, menuBar, undecorated, events, onDismissRequest) {
    onCommit {
        AppManager.focusedWindow!!.window.background = Color.BLACK
    }
    MaterialTheme {
        Scaffold(
            modifier = Modifier.clip(RoundedCornerShape(20.dp)),
            topBar = {
                Box(modifier = Modifier.fillMaxWidth()
                    .height(50.dp)
                    .background(color = MaterialTheme.colors.primary)) {
                }
            }
        ) {
            content()
        }
    }
}