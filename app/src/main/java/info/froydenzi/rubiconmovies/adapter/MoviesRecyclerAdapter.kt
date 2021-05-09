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
import info.froydenzi.rubiconmovies.data.model.MovieModel
import info.froydenzi.rubiconmovies.databinding.MoviesItemBinding
import info.froydenzi.rubiconmovies.ui.HomeFragmentDirections

class MoviesRecyclerAdapter(private val movies: MovieModel) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movItem: ConstraintLayout = view.findViewById(R.id.movie_item_click)
        val movPosition: TextView = view.findViewById(R.id.movie_number)
        val movImage: ImageView = view.findViewById(R.id.movie_image)
        val movText: TextView = view.findViewById(R.id.movie_title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movNumber = "${position.plus(1)}."
        holder.movPosition.text = movNumber
        holder.movText.text = movies.results[position].title
        Glide.with(holder.itemView)
            .load(Companion.IMAGE_URL + movies.results[position].poster_path)
            .circleCrop()
            .into(holder.movImage)

        holder.movItem.setOnClickListener {
            val id = movies.results[position].id
            val action = HomeFragmentDirections.actionHomeFragmentToSingleCallFragment(id, "movie")
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    companion object {
        const val IMAGE_URL = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/"
    }
}