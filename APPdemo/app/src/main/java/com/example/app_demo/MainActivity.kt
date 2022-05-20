package com.example.app_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app_demo.fragments.HomeFragment
import com.example.app_demo.fragments.NewCompetenceFragment
import com.example.app_demo.repositories.CompetenceRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navMenu = findViewById<BottomNavigationView>(R.id.nav_menu)

        loadFragment(HomeFragment(this), R.string.home_page_title)
        navMenu.setOnItemSelectedListener {
            val b = when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.new_competence -> {
                    loadFragment(NewCompetenceFragment(this), R.string.new_comp_title)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
            b
        }

    }

    fun loadFragment(homeFragment: Fragment
                     , homePageTitle: Int) {
        val pagetitleView = findViewById<TextView>(R.id.page_title)
        pagetitleView.text = resources.getString(homePageTitle)
        CompetenceRepository.updateAll {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, homeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}