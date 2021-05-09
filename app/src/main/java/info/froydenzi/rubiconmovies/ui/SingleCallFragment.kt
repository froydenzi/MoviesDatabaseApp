package info.froydenzi.rubiconmovies.ui

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import info.froydenzi.rubiconmovies.databinding.SingleCallFragmentBinding
import info.froydenzi.rubiconmovies.viewModel.SingleCallViewModel

class SingleCallFragment : Fragment() {

    private lateinit var viewModel: SingleCallViewModel
    private val args: SingleCallFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val scView = SingleCallFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(SingleCallViewModel::class.java)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.getCall(args.id, args.type, context)
        viewModel.mySerieCallResponse.observe(viewLifecycleOwner, {
            if (it != null) {
                scView.contentTitle.text = it.name
                Glide.with(scView.root)
                    .load(IMAGE_URL + it.poster_path)
                    .override(ActionBar.LayoutParams.MATCH_PARENT)
                    .into(scView.contentSlika)
                scView.contentText.text = it.overview
                scView.noInternet.visibility = View.GONE

                Log.d("RES - Shows name", it.name)
                Log.d("RES - Shows id", it.id.toString())
            } else {
                scView.singleCallLayer.visibility = View.GONE
                scView.noInternet.visibility = View.VISIBLE
                scView.noConnButtonCall.setOnClickListener {
                    viewModel.getCall(args.id, args.type, context)
                    scView.singleCallLayer.visibility = View.VISIBLE
                    scView.noInternet.visibility = View.GONE
                }
            }

        })
        viewModel.myMovieCallResponse.observe(viewLifecycleOwner, {
            if (it != null) {
                scView.contentTitle.text = it.title
                Glide.with(scView.root)
                    .load(IMAGE_URL + it.poster_path)
                    .override(ActionBar.LayoutParams.MATCH_PARENT)
                    .into(scView.contentSlika)
                scView.contentText.text = it.overview
                scView.noInternet.visibility = View.GONE

                Log.d("RES - Movie title", it.title)
                Log.d("RES - Movie id", it.id.toString())
            } else {
                scView.singleCallLayer.visibility = View.GONE
                scView.noInternet.visibility = View.VISIBLE
                scView.noConnButtonCall.setOnClickListener {
                    viewModel.getCall(args.id, args.type, context)
                    scView.singleCallLayer.visibility = View.VISIBLE
                    scView.noInternet.visibility = View.GONE
                }
            }

        })

        return scView.root
    }

    companion object {
        const val IMAGE_URL = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/"
    }
}