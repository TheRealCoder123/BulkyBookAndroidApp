package com.nextup.bulkybookapp.ui.activities.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivity
import com.nextup.bulkybookapp.Utils.DataStore
import com.nextup.bulkybookapp.databinding.ActivityPanelBinding
import com.nextup.bulkybookapp.ui.activities.Main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PanelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPanelBinding

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.GoBack.setOnClickListener {
            dataStore.saveBoolean(dataStore.tags().IS_IN_PANEL_MODE, false)
            startActivity(MainActivity())
            finish()
        }


    }
}