package sample

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

actual class Platform {
    actual val name: String = "iOS"
    actual val httpClientEngine: HttpClientEngine get() = Ios.create {  }
    actual val mainDispatcher: CoroutineDispatcher get() = UI()
}

class UI : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val queue = dispatch_get_main_queue()
        dispatch_async(queue) {
            block.run()
        }
    }
}
