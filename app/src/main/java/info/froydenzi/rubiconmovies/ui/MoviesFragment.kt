package info.froydenzi.rubiconmovies.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.froydenzi.rubiconmovies.adapter.MoviesRecyclerAdapter
import info.froydenzi.rubiconmovies.databinding.MoviesFragmentBinding
import info.froydenzi.rubiconmovies.viewModel.MoviesViewModel


class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movView = MoviesFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)


        viewModel.getMovies()
        viewModel.myMovieResponse.observe(viewLifecycleOwner, {
            if (it != null) {

                movView.noInternet.visibility = View.GONE
                val adapter = MoviesRecyclerAdapter(it)
                val llm = LinearLayoutManager(context)
                val recyclerView: RecyclerView = movView.moviesRecycler
                val decor = DividerItemDecoration(recyclerView.context, llm.orientation)
                recyclerView.addItemDecoration(decor)
                recyclerView.layoutManager = llm
                recyclerView.adapter = adapter

                Log.d("RES - Movies pages", it.page.toString())
                Log.d("RES - Movies count", it.total_results.toString())
            } else {
                movView.noInternet.visibility = View.VISIBLE
                movView.noConnButtonCall.setOnClickListener {
                    viewModel.getMovies()
                    movView.noInternet.visibility = View.GONE
                }
            }
        })
        return movView.root
    }
}