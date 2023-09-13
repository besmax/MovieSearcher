package bes.max.moviesearcher.ui.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.databinding.CastListItemBinding
import bes.max.moviesearcher.databinding.FragmentCastBinding
import bes.max.moviesearcher.domain.models.MovieCastPerson

class CastViewHolder(val binding: CastListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieCastPerson: MovieCastPerson) {

    }

    companion object {
        fun inflateFrom(parent: ViewGroup) : CastViewHolder {
            val layouInflater = LayoutInflater.from(parent.context)
            val binding = CastListItemBinding.inflate(layouInflater, parent, false)
            return CastViewHolder(binding)
        }
    }
}