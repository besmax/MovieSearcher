package bes.max.moviesearcher.ui.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import bes.max.moviesearcher.ui.details.about.AboutFragment
import bes.max.moviesearcher.ui.details.poster.PosterFragment

private const val FRAGMENT_NUMBER = 2

class DetailsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = FRAGMENT_NUMBER

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PosterFragment.newInstance()
            else -> AboutFragment.newInstance()
        }
    }
}