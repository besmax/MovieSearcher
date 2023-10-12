package bes.max.moviesearcher.ui.names

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.ListItemPersonBinding
import bes.max.moviesearcher.domain.models.Person
import com.bumptech.glide.Glide

class PersonViewHolder(val binding: ListItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person) {
        Glide.with(itemView)
            .load(person.photoUrl)
            .placeholder(R.drawable.ic_person)
            .circleCrop()
            .into(binding.photo)

        with(binding) {
            name.text = person.name
            description.text = person.description
        }
    }

    companion object {
        fun inflateFrom(parent: ViewGroup): PersonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemPersonBinding.inflate(layoutInflater, parent, false)
            return PersonViewHolder(binding)
        }
    }

}