package base

import androidx.compose.desktop.*
import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import ext.closeWindow
import ext.getWindow
import java.awt.Color
import java.awt.Container
import java.awt.image.BufferedImage
import javax.swing.JButton
import javax.swing.JFrame

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
        undecorated: Boolean = false, // 显示隐藏窗口边框
        events: WindowEvents = WindowEvents(),
        onDismissRequest: (() -> Unit)? = null,
        content: @Composable () -> Unit = {}
) = Window(title, size, location, centered, icon, menuBar, undecorated, events, onDismissRequest) {
    MaterialTheme {
        Scaffold(
//            modifier = Modifier.clip(RoundedCornerShape(20.dp)),
            topBar = if (undecorated) topBar() else { {} }
        ) {
            content()
        }
    }
}

private fun topBar(): @Composable () -> Unit = {
    Row(
            modifier = Modifier.fillMaxWidth()
                    .height(40.dp)
                    .background(color = MaterialTheme.colors.primary)
                    .dragGestureFilter(
                            dragObserver = object : DragObserver {
                                override fun onDrag(dragDistance: Offset): Offset {
                                    getWindow()?.apply {
                                        setLocation(x + dragDistance.x.toInt(), y + dragDistance.y.toInt())
                                    }
                                    return super.onDrag(dragDistance)
                                }
                            },
                            canDrag = { true }
                    ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            closeWindow()
        }) {
            Icon(
                    imageVector = Icons.Rounded.Close,
                    modifier = Modifier.clip(CircleShape)
                            .background(MaterialTheme.colors.background),
                    tint = MaterialTheme.colors.primary
            )
        }
    }
}