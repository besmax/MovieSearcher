package bes.max.moviesearcher.ui.cast

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.R
import bes.max.moviesearcher.domain.models.MovieCastPerson

class CastListAdapter :
    ListAdapter<MovieCastRVItem, RecyclerView.ViewHolder>(CastDiffItemCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MovieCastRVItem.HeaderTitle -> R.layout.cast_list_item_header
            is MovieCastRVItem.PersonItem -> R.layout.cast_list_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.cast_list_item_header -> CastHeaderViewHolder.inflateFrom(parent)
            R.layout.cast_list_item -> CastViewHolder.inflateFrom(parent)
            else -> error("Unknown viewType bind $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.cast_list_item_header -> {
                (holder as CastHeaderViewHolder).bind(currentList[position] as MovieCastRVItem.HeaderTitle)
            }

            R.layout.cast_list_item -> {
                (holder as CastViewHolder).bind(currentList[position] as MovieCastRVItem.PersonItem)
            }

            else -> error("Unknown viewType bind [${holder.itemViewType}]")
        }
    }

}