package ext

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun launch(block: suspend () -> Unit = {}) {
    GlobalScope.launch {
        block()
    }
}