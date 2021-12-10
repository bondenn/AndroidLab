package eu.gitgud.androidlab

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Find the navigation host fragments which contains the 'current destination fragment'
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment

        navController = navHostFragment.navController

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // Create an app bar configuration, the set is 'top level destinations'.
        // These destinations will have the hamburger menu showing on them instead
        // of a back arrow to go back to the previous destination.
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.HomeFragment), drawerLayout)
        navigationView.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navigationView.menu.findItem(R.id.LogoutAction).setOnMenuItemClickListener { item ->
            Log.d(TAG, "onOptionsItemSelected: ${item.title}")
            drawerLayout.close()
            return@setOnMenuItemClickListener true
        }
    }

}