package net.android.breakingbadapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import net.android.breakingbadapp.ui.CharacterFragment
import net.android.breakingbadapp.ui.HomeFragment

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    val items = 2
    override fun getItemCount(): Int {
        return items
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 ->
                HomeFragment()
            1 ->
                CharacterFragment()
            else ->
                HomeFragment()
        }
    }
}