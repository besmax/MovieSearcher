package bes.max.moviesearcher.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import bes.max.moviesearcher.domain.models.Person

class PersonListAdapter : ListAdapter<Person, PersonViewHolder>(PersonDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(
            getItem(position)
        )
    }
}