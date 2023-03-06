package com.nextup.bulkybookapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    var fragments: List<Fragment>,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return  fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return  fragments[position]
    }


}