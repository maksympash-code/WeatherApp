package ua.knu.maksym_pashchenko.weatherapp.presentation.navigation

object Routes {
    const val SEARCH = "search"
    const val DETAILS = "details/{city}"

    fun details(city: String): String {
        return "details/$city"
    }
}