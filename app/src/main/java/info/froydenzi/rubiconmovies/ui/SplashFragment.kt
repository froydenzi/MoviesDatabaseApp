package info.froydenzi.rubiconmovies.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import info.froydenzi.rubiconmovies.R
import info.froydenzi.rubiconmovies.databinding.SplashFragmentBinding


@Suppress("DEPRECATION")
class SplashFragment : Fragment() {

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SplashFragmentBinding.inflate(layoutInflater)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        Handler().postDelayed({
            binding.root.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }, 2000)
        return binding.root
    }

}