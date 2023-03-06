package com.nextup.bulkybookapp.ui.fragments.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextup.bulkybookapp.R
import com.nextup.bulkybookapp.databinding.FragmentOnBoardingPageOneBinding
import com.nextup.bulkybookapp.ui.activities.Auth.OnBoardingActivity


class OnBoardingPageOne : Fragment() {

    private lateinit var binding: FragmentOnBoardingPageOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingPageOneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}