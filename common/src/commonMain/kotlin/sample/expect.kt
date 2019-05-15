package sample

import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.CoroutineDispatcher

expect class Platform() {
    val name: String
    val httpClientEngine: HttpClientEngine
    val mainDispatcher: CoroutineDispatcher
}

class Proxy {
    fun proxyHello() = "Hello from ${Platform().name}"
}
