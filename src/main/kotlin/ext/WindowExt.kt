package ext

import androidx.compose.desktop.AppFrame
import androidx.compose.desktop.AppManager

fun getWindow(): AppFrame? = AppManager.focusedWindow

fun closeWindow() = getWindow()?.close()

fun disposeWindow() = getWindow()?.window?.dispose()

fun finishApp() = AppManager.exit()