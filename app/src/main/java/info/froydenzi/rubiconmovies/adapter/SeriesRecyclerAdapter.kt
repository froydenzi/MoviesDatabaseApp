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
import info.froydenzi.rubiconmovies.data.model.SerieModel
import info.froydenzi.rubiconmovies.databinding.TvshowsItemBinding
import info.froydenzi.rubiconmovies.ui.HomeFragmentDirections

class SeriesRecyclerAdapter(private val series: SerieModel) :
    RecyclerView.Adapter<SeriesRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val serItem: ConstraintLayout = view.findViewById(R.id.tvshow_item_click)
        val serPosition: TextView = view.findViewById(R.id.serie_number)
        val serImage: ImageView = view.findViewById(R.id.tvshow_image)
        val serText: TextView = view.findViewById(R.id.tvshow_title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TvshowsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movNumber = "${position.plus(1)}."
        holder.serPosition.text = movNumber
        holder.serText.text = series.results[position].name
        Glide.with(holder.itemView)
            .load(Companion.IMAGE_URL + series.results[position].poster_path)
            .circleCrop()
            .into(holder.serImage)

        holder.serItem.setOnClickListener {
            val id = series.results[position].id
            val action = HomeFragmentDirections.actionHomeFragmentToSingleCallFragment(id, "serie")
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