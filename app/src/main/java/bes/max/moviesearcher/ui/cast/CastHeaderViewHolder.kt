package bes.max.moviesearcher.ui.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.databinding.CastListItemHeaderBinding

class CastHeaderViewHolder(private val binding: CastListItemHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movieCastRVItem: MovieCastRVItem.HeaderTitle) {
        binding.castListItemHeaderTitle.text = movieCastRVItem.headerText
    }

        companion object {
            fun inflateFrom(parent: ViewGroup) : CastHeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CastListItemHeaderBinding.inflate(layoutInflater, parent, false)
                return CastHeaderViewHolder(binding)
            }
        }
}