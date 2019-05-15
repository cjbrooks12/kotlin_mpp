package sample.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val coord: WeatherCoordinate,
    val weather: List<Weather>,
    val base: String,
    val main: WeatherMain,
    val name: String
)

@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class WeatherCoordinate(
    val lon: Float,
    val lat: Float
)

@Serializable
data class WeatherMain(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    val temp_min: Double,
    val temp_max: Double
)
