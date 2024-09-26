package ru.practicum.android.diploma.root.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRootBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navigationView.setupWithNavController(navController)

        val listFilterFragment = listOf(
            R.id.filterIndustryFragment,
            R.id.filterSettingsFragment,
            R.id.choosingAPlaceOfWorkFragment,
            R.id.countryFragment,
            R.id.areaSelectFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.vacancyFragment -> binding.navigationView.isVisible = false
                in listFilterFragment -> binding.navigationView.isVisible = false
                else -> binding.navigationView.isVisible = true
            }
        }
    }

}
