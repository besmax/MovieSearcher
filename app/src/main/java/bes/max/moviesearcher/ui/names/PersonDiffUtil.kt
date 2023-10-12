package bes.max.moviesearcher.ui.names

import androidx.recyclerview.widget.DiffUtil
import bes.max.moviesearcher.domain.models.Person

class PersonDiffUtil : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem
}