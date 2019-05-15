package sample.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.serialization.json.Json
import sample.Platform
import sample.models.Weather
import sample.models.WeatherCoordinate
import sample.models.WeatherData
import sample.models.WeatherMain

class WeatherApi {
    companion object {
        private const val baseUrl = "https://samples.openweathermap.org"
    }

    val platform: Platform by lazy { Platform() }

    private val client: HttpClient by lazy {
        HttpClient(platform.httpClientEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict).apply {
                    register(WeatherData.serializer())
                    register(Weather.serializer())
                    register(WeatherCoordinate.serializer())
                    register(WeatherMain.serializer())
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    suspend fun fetchWeather(location: String): WeatherData {
        return client.get {
            url("$baseUrl/data/2.5/weather?q=$location&appid=b6907d289e10d714a6e88b30761fae22")
        }
    }

}

