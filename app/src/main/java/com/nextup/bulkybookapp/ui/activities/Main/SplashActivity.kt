package com.nextup.bulkybookapp.ui.activities.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nextup.bulkybookapp.Utils.DataStore
import com.nextup.bulkybookapp.databinding.ActivitySplashBinding
import com.nextup.bulkybookapp.ui.activities.Auth.AuthActivity
import com.nextup.bulkybookapp.ui.activities.Auth.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = dataStore.readString(dataStore.tags().USER_IDENTIFIER_TAG)
        val isOnBoardingFinished = dataStore.readBoolean(dataStore.tags().ON_BOARDING_VIEW_FINISHED_TAG)

        if (!isOnBoardingFinished){
            startActivity(OnBoardingActivity())
        }else if (userId == null){
            startActivity(AuthActivity())
        }else{
            startActivity(MainActivity())
        }




    }

    private fun startActivity(activity: AppCompatActivity) {
        Intent(this, activity::class.java).also {intent->
            Handler().postDelayed(
                {
                    startActivity(intent)
                    finish()
                }, 2000
            )
        }
    }

}