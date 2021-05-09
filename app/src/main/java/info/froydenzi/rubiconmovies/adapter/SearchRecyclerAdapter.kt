package info.froydenzi.rubiconmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.froydenzi.rubiconmovies.R
import info.froydenzi.rubiconmovies.data.model.searchModel
import info.froydenzi.rubiconmovies.databinding.SearchItemBinding
import info.froydenzi.rubiconmovies.ui.SearchFragmentDirections

class SearchRecyclerAdapter(private val search: searchModel) :
    RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchItem: ConstraintLayout = view.findViewById(R.id.search_item)
        val searchImage: ImageView = view.findViewById(R.id.search_item_image)
        val searchText: TextView = view.findViewById(R.id.search_item_text)
        val searchType: TextView = view.findViewById(R.id.search_item_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(IMAGE_URL + search.results[position].poster_path)
            .circleCrop()
            .into(holder.searchImage)
        if (search.results[position].media_type == "movie") {
            holder.searchText.text = search.results[position].title
            holder.searchType.text = holder.itemView.resources.getString(R.string.movie)
            holder.searchItem.setOnClickListener {
                val idMovie = search.results[position].id
                val action = SearchFragmentDirections.actionSearchFragmentToSingleCallFragment(idMovie, "movie")
                holder.itemView.findNavController().navigate(action)
            }
        } else if (search.results[position].media_type == "tv") {
            holder.searchText.text = search.results[position].name
            holder.searchType.text = holder.itemView.resources.getString(R.string.serie)
            holder.searchItem.setOnClickListener {
                val idSerie = search.results[position].id
                val action = SearchFragmentDirections.actionSearchFragmentToSingleCallFragment(idSerie, "serie")
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return search.results.size
    }

    companion object {
        const val IMAGE_URL = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/"
    }
}