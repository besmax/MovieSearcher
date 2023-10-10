package bes.max.moviesearcher.data.mappers

import bes.max.moviesearcher.data.dto.PersonDto
import bes.max.moviesearcher.domain.models.Person

class PersonDtoMapper {

    fun map(person: PersonDto): Person =
        Person(
            id = person.id,
            name = person.title,
            description = person.description,
            photoUrl = person.image
        )
}