package bes.max.moviesearcher.ui.cast

import androidx.recyclerview.widget.DiffUtil
import bes.max.moviesearcher.domain.models.MovieCastPerson

class CastDiffItemCallback : DiffUtil.ItemCallback<MovieCastPerson>() {
    override fun areItemsTheSame(oldItem: MovieCastPerson, newItem: MovieCastPerson): Boolean =
        (oldItem == newItem)

    override fun areContentsTheSame(oldItem: MovieCastPerson, newItem: MovieCastPerson): Boolean =
        (oldItem.id == newItem.id)
}