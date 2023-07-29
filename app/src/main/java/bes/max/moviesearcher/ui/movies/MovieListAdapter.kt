package bes.max.moviesearcher.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.R
import bes.max.moviesearcher.domain.models.Movie
import com.bumptech.glide.Glide

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    var movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent)

    override fun getItemCount(): Int = movies.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
    ) {

        fun bind(model: Movie) {
            val poster = itemView.findViewById<ImageView>(R.id.poster_movie_list_item)
            val title = itemView.findViewById<TextView>(R.id.title_movie_list_item)
            val desription = itemView.findViewById<TextView>(R.id.description_movie_list_item)

            Glide.with(itemView)
                .load(model.image)
                .into(poster)
            title.setText(model.title)
            desription.setText(model.description)
        }
    }


}