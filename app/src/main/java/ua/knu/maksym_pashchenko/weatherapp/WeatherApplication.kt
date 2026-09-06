package ua.knu.maksym_pashchenko.weatherapp

import android.app.Application
import ua.knu.maksym_pashchenko.weatherapp.di.AppContainer

class WeatherApplication: Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(
            context = applicationContext
        )
    }
}