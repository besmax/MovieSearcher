package bes.max.moviesearcher.ui.cast

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import bes.max.moviesearcher.domain.models.MovieCastPerson

class CastListAdapter : ListAdapter<MovieCastPerson, CastViewHolder>(CastDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


}