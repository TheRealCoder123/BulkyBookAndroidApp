package com.nextup.bulkybookapp.ui.activities.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.Utils.AndroidUtils.startActivity
import com.nextup.bulkybookapp.Utils.DataStore
import com.nextup.bulkybookapp.databinding.ActivityOnBoardingBinding
import com.nextup.bulkybookapp.ui.adapters.ViewPagerAdapter
import com.nextup.bulkybookapp.ui.fragments.Auth.OnBoardingPageOne
import com.nextup.bulkybookapp.ui.fragments.Auth.OnBoardingPageThree
import com.nextup.bulkybookapp.ui.fragments.Auth.OnBoardingPageTwo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pager = binding.pager
        val tabLayout = binding.tabLayout

        val fragments = listOf<Fragment>(
            OnBoardingPageOne(),
            OnBoardingPageTwo(),
            OnBoardingPageThree()
        )

        val pagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager)

        pager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(pager, true);


        binding.Next.setOnClickListener {
            startActivity(AuthActivity())
            finish()
            dataStore.saveBoolean(dataStore.tags().ON_BOARDING_VIEW_FINISHED_TAG, true)
        }



    }

    fun getNextButton() : Button {
        return  binding.Next
    }

}