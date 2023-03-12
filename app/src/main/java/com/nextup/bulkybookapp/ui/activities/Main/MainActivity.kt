package com.nextup.bulkybookapp.ui.activities.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivity
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivityFinishBackground
import com.nextup.bulkybookapp.Utils.DataStore
import com.nextup.bulkybookapp.databinding.ActivityMainBinding
import com.nextup.bulkybookapp.ui.activities.Admin.PanelActivity
import com.nextup.bulkybookapp.ui.activities.Auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.FragmentContainer) as NavHostFragment
        setupWithNavController(binding.BottomNav, navHostFragment.navController)

        val userRole = dataStore.readString(dataStore.tags().USER_ROLE_TAG)
        val userId = dataStore.readString(dataStore.tags().USER_IDENTIFIER_TAG)

        if (userRole == "Admin" || userRole == "Moderator"){
            binding.AdminModPanel.visibility = View.VISIBLE
        }else{
            binding.AdminModPanel.visibility = View.GONE

        }


        if(userId == null && userRole == null){
            binding.SignIn.visibility = View.VISIBLE
            binding.Cart.visibility = View.GONE
            binding.Wishlist.visibility = View.GONE
            binding.AdminModPanel.visibility = View.GONE
        }

        binding.SignIn.setOnClickListener {
            startActivity(AuthActivity())
        }

        binding.AdminModPanel.setOnClickListener {
            startActivityFinishBackground(PanelActivity())
            dataStore.saveBoolean(dataStore.tags().IS_IN_PANEL_MODE, true)
        }

        binding.Cart.setOnClickListener {
            dataStore.saveString(dataStore.tags().TOKEN_TAG, null)
            dataStore.saveString(dataStore.tags().USER_IDENTIFIER_TAG, null)
            dataStore.saveString(dataStore.tags().USER_ROLE_TAG, null)
            startActivity(AuthActivity())
            finish()
        }


    }
}