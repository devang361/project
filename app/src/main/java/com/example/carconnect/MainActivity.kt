package com.example.carconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.carconnect.fragments.AccountFragment
import com.example.carconnect.fragments.FavouritesFragment
import com.example.carconnect.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.ImageView
import androidx.core.view.GravityCompat



class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val favouritesFragment = FavouritesFragment()
    private val accountFragment = AccountFragment()

    lateinit var  toggel : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCurrentFragment(homeFragment)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation) // Updated ID here

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_favorite -> makeCurrentFragment(favouritesFragment)
                R.id.ic_account -> makeCurrentFragment(accountFragment)
            }
            true
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val burgerIconImageView: ImageView = findViewById(R.id.burgericon)

        burgerIconImageView.setOnClickListener {

            drawerLayout.openDrawer(GravityCompat.START)

            toggel = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggel)

            toggel.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        true
                    }

//                R.id.nav_fav -> {
//                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this,MainActivity::class.java)
//                    startActivity(intent)
//                    true
//                }


                    R.id.sell_car -> {
                        val intent = Intent(this, sellcar::class.java)
                        startActivity(intent)
                        true
                    }

                    else -> false
                }
            }

        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}

