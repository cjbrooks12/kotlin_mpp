package sample

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Platform {
    actual val name: String = "Android"
    actual val httpClientEngine: HttpClientEngine = OkHttpEngine(OkHttpConfig())
    actual val mainDispatcher: CoroutineDispatcher get() = Dispatchers.Main

}
