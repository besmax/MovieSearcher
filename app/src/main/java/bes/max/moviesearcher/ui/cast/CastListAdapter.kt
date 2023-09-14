package bes.max.moviesearcher.ui.cast

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import bes.max.moviesearcher.R
import bes.max.moviesearcher.domain.models.MovieCastPerson

class CastListAdapter : ListAdapter<MovieCastRVItem, CastViewHolder>(CastDiffItemCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MovieCastRVItem.HeaderTitle -> R.layout.cast_list_item_header
            is MovieCastRVItem.PersonItem -> R.layout.cast_list_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


}