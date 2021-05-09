package info.froydenzi.rubiconmovies.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.froydenzi.rubiconmovies.adapter.SearchRecyclerAdapter
import info.froydenzi.rubiconmovies.databinding.SearchFragmentBinding
import info.froydenzi.rubiconmovies.viewModel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val serView = SearchFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Search tab"


        serView.searchEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                Log.d("TEXT INPUT", "Text size: ${text.length}")
                if (text.length > 3) {
                    val searchQuery = serView.searchEditText.text.toString()
                    viewModel.getSearch(searchQuery, context)
                }
            }
        }

        viewModel.mySearchCallResponse.observe(viewLifecycleOwner, {
            if (it != null) {

                val adapter = SearchRecyclerAdapter(it)
                val llm = LinearLayoutManager(context)
                val recyclerView: RecyclerView = serView.searchRecycler
                recyclerView.layoutManager = llm
                recyclerView.adapter = adapter

                Log.d("RES - Search items", it.results.toString())
            } else {
                Toast.makeText(context, "No internet connection...", Toast.LENGTH_SHORT).show()
            }

        })
        return serView.root
    }

}