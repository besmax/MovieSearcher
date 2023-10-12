package bes.max.moviesearcher.domain.api

import bes.max.moviesearcher.domain.models.Person
import bes.max.moviesearcher.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {

    fun getNames(expression: String): Flow<Resource<List<Person>>>

}