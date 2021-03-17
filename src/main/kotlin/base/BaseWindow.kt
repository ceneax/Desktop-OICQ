package base

import androidx.compose.desktop.AppFrame
import androidx.compose.desktop.AppWindow
import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.MenuBar
import java.awt.image.BufferedImage

abstract class BaseWindow(
    title: String = "",
    size: IntSize = IntSize(800, 600),
    location: IntOffset = IntOffset.Zero,
    centered: Boolean = true,
    icon: BufferedImage? = null,
    menuBar: MenuBar? = null,
    undecorated: Boolean = false
) {

    private val mContent: @Composable () -> Unit
    private val mEvents: WindowEvents = WindowEvents()

    init {
        mContent = setContent()

        mEvents.onOpen = {
            onOpen()
        }
        mEvents.onClose = {
            onClose()
        }
        mEvents.onResize = {
            onResize(it)
        }

        Window(title, size, location, centered, icon, menuBar, undecorated, mEvents, ::onDismissRequest, mContent)
    }

    abstract fun setContent(): @Composable () -> Unit

    open fun onOpen() {}
    open fun onClose() {}
    open fun onDismissRequest() {}
    open fun onResize(size: IntSize) {}

}