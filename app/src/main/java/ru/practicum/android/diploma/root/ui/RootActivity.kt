package ru.practicum.android.diploma.root.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRootBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.searchFragment, R.id.favoritesFragment, R.id.aboutFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
        }

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)

        // Доступ к baseUrl HH так же через BuildConfig
        BuildConfig.HH_BASE_URL
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
