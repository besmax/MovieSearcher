package bes.max.moviesearcher.ui.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.CastListItemBinding
import bes.max.moviesearcher.databinding.FragmentCastBinding
import bes.max.moviesearcher.domain.models.MovieCastPerson
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class CastViewHolder(val binding: CastListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieCastPerson: MovieCastPerson) {
        if (movieCastPerson.image == null) {
            binding.actorImageView.isVisible = false
        } else {
            Glide.with(binding.root)
                .load(movieCastPerson.image)
                .placeholder(R.drawable.not_found)
                .transform(MultiTransformation(FitCenter(), RoundedCorners(10)))
                .into(binding.actorImageView)
        }
        binding.actorNameTextView.text = movieCastPerson.name
        binding.actorDescriptionTextView.text = movieCastPerson.description

    }
 
    companion object {
        fun inflateFrom(parent: ViewGroup) : CastViewHolder {
            val layouInflater = LayoutInflater.from(parent.context)
            val binding = CastListItemBinding.inflate(layouInflater, parent, false)
            return CastViewHolder(binding)
        }
    }
}