package bes.max.moviesearcher.ui.names

import bes.max.moviesearcher.domain.models.Person

sealed interface NamesScreenState {
    object NotStarted: NamesScreenState

    data class ShowContent(
        val names: List<Person> = emptyList()
    ): NamesScreenState

    object NothingFound: NamesScreenState

    object Loading: NamesScreenState

    data class Error(
        val noInternet: Boolean = false
    ): NamesScreenState {
    }
}