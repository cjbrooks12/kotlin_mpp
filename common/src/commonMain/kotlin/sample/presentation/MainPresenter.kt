package sample.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sample.Proxy
import sample.api.WeatherApi
import kotlin.math.roundToInt

class MainPresenter(
    mainView: MainView
) : BasePresenter<MainView>(mainView), MainView by mainView {

    val weatherApi: WeatherApi by lazy { WeatherApi() }

    override fun onStart(): MainPresenter {
        setButtonText(Proxy().proxyHello())
        return this
    }

    fun searchWeather(location: String) {
        if (location.isBlank()) return showError("Input cannot be blank")

        setButtonText("Loading...")
        setTextContent("")

        CoroutineScope(weatherApi.platform.mainDispatcher).launch {
            val weather = weatherApi.fetchWeather(location)
            setButtonText("Search!")
            setTextContent(
                """
                |Current weather for ${weather.name} (${weather.coord.lat}, ${weather.coord.lon})
                |
                |${weather.weather.joinToString("\n") { "- ${it.description}" }}
                |
                |Currently: ${weather.main.temp.k_to_f()}F
                |High: ${weather.main.temp_max.k_to_f()}F
                |Low: ${weather.main.temp_min.k_to_f()}F
            """.trimMargin()
            )
        }
    }

    private fun Double.k_to_f(): Int {
        return (9.0 / 5.0 * (this - 273.0) + 32.0).roundToInt()
    }
}

interface MainView : BaseView {
    fun setButtonText(text: String)
    fun setTextContent(text: String)
}
