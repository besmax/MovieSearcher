package bes.max.moviesearcher.ui.cast

import androidx.recyclerview.widget.DiffUtil
import bes.max.moviesearcher.domain.models.MovieCastPerson

//class CastDiffItemCallback : DiffUtil.ItemCallback<MovieCastPerson>() {
//    override fun areItemsTheSame(oldItem: MovieCastPerson, newItem: MovieCastPerson): Boolean =
//        (oldItem == newItem)
//
//    override fun areContentsTheSame(oldItem: MovieCastPerson, newItem: MovieCastPerson): Boolean =
//        (oldItem.id == newItem.id)
//}

class CastDiffItemCallback : DiffUtil.ItemCallback<MovieCastRVItem>() {
    override fun areItemsTheSame(oldItem: MovieCastRVItem, newItem: MovieCastRVItem): Boolean {
        return if(oldItem is MovieCastRVItem.HeaderTitle && newItem is MovieCastRVItem.HeaderTitle) {
            oldItem.headerText == newItem.headerText
        } else if(oldItem is MovieCastRVItem.PersonItem && newItem is MovieCastRVItem.PersonItem) {
            oldItem.data.id == newItem.data.id
        } else false
    }

    override fun areContentsTheSame(oldItem: MovieCastRVItem, newItem: MovieCastRVItem): Boolean {
        return if(oldItem is MovieCastRVItem.HeaderTitle && newItem is MovieCastRVItem.HeaderTitle) {
            oldItem.headerText == newItem.headerText
        } else if(oldItem is MovieCastRVItem.PersonItem && newItem is MovieCastRVItem.PersonItem) {
            oldItem.data == newItem.data
        } else false
    }
}