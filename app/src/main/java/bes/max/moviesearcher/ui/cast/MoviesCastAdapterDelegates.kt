package bes.max.moviesearcher.ui.cast

import androidx.core.view.isVisible
import bes.max.moviesearcher.R
import bes.max.moviesearcher.core.ui.RVItem
import bes.max.moviesearcher.databinding.CastListItemBinding
import bes.max.moviesearcher.databinding.CastListItemHeaderBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun movieCastHeaderDelegate() =
    adapterDelegateViewBinding<MovieCastRVItem.HeaderTitle, RVItem, CastListItemHeaderBinding>(
        { layoutInflater, root -> CastListItemHeaderBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.castListItemHeaderTitle.text = item.headerText
        }
    }

fun movieCastPersonDelegate() =
    adapterDelegateViewBinding<MovieCastRVItem.PersonItem, RVItem, CastListItemBinding>(
        { layoutInflater, root -> CastListItemBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            if (item.data.image == null) {
                binding.actorImageView.isVisible = false
            } else {
                Glide.with(binding.root)
                    .load(item.data.image)
                    .placeholder(R.drawable.not_found)
                    .transform(MultiTransformation(FitCenter(), RoundedCorners(10)))
                    .into(binding.actorImageView)
                binding.actorImageView.isVisible = true
            }
            binding.actorNameTextView.text = item.data.name
            binding.actorDescriptionTextView.text = item.data.description
        }
    }