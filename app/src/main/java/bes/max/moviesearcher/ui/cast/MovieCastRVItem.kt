package bes.max.moviesearcher.ui.cast

import bes.max.moviesearcher.domain.models.MovieCastPerson

sealed interface MovieCastRVItem {
    data class HeaderTitle(
        val headerText: String
    ) : MovieCastRVItem

    data class PersonItem(
        val data: MovieCastPerson
    ): MovieCastRVItem
}