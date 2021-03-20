package ext

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun launch(block: suspend () -> Unit = {}) {
    GlobalScope.launch {
        block()
    }
}

fun Boolean.yes(block: () -> Unit): Boolean {
    if (this) {
        block()
    }
    return this
}
fun Boolean.no(block: () -> Unit): Boolean {
    if (!this) {
        block()
    }
    return this
}