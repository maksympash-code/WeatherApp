package ua.knu.maksym_pashchenko.weatherapp.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.knu.maksym_pashchenko.weatherapp.domain.repository.WeatherRepository
import ua.knu.maksym_pashchenko.weatherapp.presentation.details.WeatherDetailsScreen
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.SearchScreen
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel.SearchViewModel
import ua.knu.maksym_pashchenko.weatherapp.presentation.search.viewmodel.SearchViewModelFactory

@Composable
fun AppNavGraph(
    weatherRepository: WeatherRepository
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SEARCH
    ) {
        composable(
            route = Routes.SEARCH
        ) {
            val searchViewModel: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(weatherRepository)
            )
            
            SearchScreen(
                viewModel = searchViewModel,
                onDetailsClick = { city ->
                    val encodedCity = Uri.encode(city)
                    navController.navigate(Routes.details(encodedCity))
                }
            )
        }

        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            val city = backStackEntry.arguments?.getString("city").orEmpty()

            WeatherDetailsScreen(
                city = city,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}