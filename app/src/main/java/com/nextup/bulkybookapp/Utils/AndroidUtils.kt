package com.nextup.bulkybookapp.Utils

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

object AndroidUtils {

    fun AppCompatActivity.startActivity(startActivity: AppCompatActivity) {
        Intent(this, startActivity::class.java).also { intent->
            startActivity(intent)
        }
    }

}