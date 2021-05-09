package info.froydenzi.rubiconmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import info.froydenzi.rubiconmovies.adapter.ViewPagerAdapter
import info.froydenzi.rubiconmovies.databinding.HomeFragmentBinding


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "RUBICON App"

        binding = HomeFragmentBinding.inflate(layoutInflater)
        binding.viewPager2.adapter = ViewPagerAdapter(childFragmentManager)
        binding.fragmentMenu.setupWithViewPager(binding.viewPager2)

        binding.floatingActionButton.setOnClickListener {
            binding.root.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
        return binding.root
    }


}