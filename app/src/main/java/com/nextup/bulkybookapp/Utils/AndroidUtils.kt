package com.nextup.bulkybookapp.Utils

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.Utils.AndroidUtils.showLoadingDialog
import com.nextup.bulkybookapp.ui.activities.Main.MainActivity
import io.github.muddz.styleabletoast.StyleableToast

object AndroidUtils {

    fun AppCompatActivity.startActivity(startActivity: AppCompatActivity) {
        Intent(this, startActivity::class.java).also { intent->
            startActivity(intent)
        }
    }

    fun Fragment.startActivity(startActivity: AppCompatActivity)  {
       Intent(requireActivity(), startActivity::class.java).also { intent->
            startActivity(intent)
        }
    }

    fun AppCompatActivity.startActivityFinishBackground(startActivity: AppCompatActivity) {
        val intent = Intent(this, startActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun Fragment.startActivityFinishBackground(startActivity: AppCompatActivity)  {
        val intent = Intent(requireActivity(), startActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun AppCompatActivity.initLoadingDialog(){
        LoadingDialog.initLoadingDialog(this)
    }

    fun Fragment.initLoadingDialog(){
        LoadingDialog.initLoadingDialog(requireContext())
    }

    fun AppCompatActivity.showLoadingDialog(){
        LoadingDialog.showLoadingDialog()
    }

    fun AppCompatActivity.hideLoadingDialog(){
        LoadingDialog.hideLoadingDialog()
    }

    fun Fragment.showLoadingDialog(){
        LoadingDialog.showLoadingDialog()
    }

    fun Fragment.hideLoadingDialog(){
        LoadingDialog.hideLoadingDialog()
    }

    fun Fragment.toast(
        text: String,
        gravity: Int = Gravity.CENTER,
        cornerRadius: Int = 10
    ): StyleableToast.Builder {

        val styleableToast = StyleableToast.Builder(requireContext())

        styleableToast .text(text)
            .textColor(Color.WHITE)
            .textBold()
            .backgroundColor(resources.getColor(R.color.toast_color))
            .gravity(gravity)
            .cornerRadius(cornerRadius)
            .show()

        return styleableToast
    }



}