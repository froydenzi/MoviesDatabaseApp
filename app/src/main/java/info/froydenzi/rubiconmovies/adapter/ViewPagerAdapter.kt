package info.froydenzi.rubiconmovies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import info.froydenzi.rubiconmovies.ui.MoviesFragment
import info.froydenzi.rubiconmovies.ui.TvShowFragment

@Suppress("DEPRECATION")
class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "MOVIES"
            1 -> return "TV SHOWS"
        }
        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> TvShowFragment()
            else -> MoviesFragment()
        }
    }
}