package com.contacts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.contacts.Fragments.ContactFragment
import com.contacts.Fragments.RecentFragment
import com.contacts.Fragments.SettingFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: CurvedBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupWindowInsets()
        setupBottomNavigation()

        replaceFragment(ContactFragment())
        bottomNavigation.show(2)
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation)

        with(bottomNavigation) {
            add(CurvedBottomNavigation.Model(1, resources.getString(R.string.recent), R.drawable.baseline_access_time_24))
            add(CurvedBottomNavigation.Model(2,  resources.getString(R.string.app_name), R.drawable.contacts))
            add(CurvedBottomNavigation.Model(3,  resources.getString(R.string.setting), R.drawable.settings))

            setOnClickMenuListener { item ->
                when (item.id) {
                    1 -> replaceFragment(RecentFragment())
                    2 -> replaceFragment(ContactFragment())
                    3 -> replaceFragment(SettingFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}