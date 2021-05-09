package info.froydenzi.rubiconmovies.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.froydenzi.rubiconmovies.adapter.SeriesRecyclerAdapter
import info.froydenzi.rubiconmovies.databinding.TvShowFragmentBinding
import info.froydenzi.rubiconmovies.viewModel.TvShowViewModel


class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tvsView = TvShowFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)


        viewModel.getSeries()
        viewModel.mySerieResponse.observe(viewLifecycleOwner, {
            if (it != null) {

                tvsView.noInternet.visibility = View.GONE
                val adapter = SeriesRecyclerAdapter(it)
                val llm = LinearLayoutManager(context)
                val recyclerView: RecyclerView = tvsView.seriesRecycler
                val decor = DividerItemDecoration(recyclerView.context, llm.orientation)
                recyclerView.addItemDecoration(decor)
                recyclerView.layoutManager = llm
                recyclerView.adapter = adapter

                Log.d("RES - Shows pages", it.page.toString())
                Log.d("RES - Shows count", it.total_results.toString())
            } else {
                tvsView.noInternet.visibility = View.VISIBLE
                tvsView.noConnButtonTv.setOnClickListener {
                    viewModel.getSeries()
                    tvsView.noInternet.visibility = View.GONE
                }
            }

        })

        return tvsView.root
    }
}