package bes.max.moviesearcher.ui.cast

import bes.max.moviesearcher.domain.models.MovieFullCast

sealed interface CastScreenState {

    object Loading : CastScreenState

    data class Content(
        val fullTitle: String,
        val items: List<MovieCastRVItem>
    ) : CastScreenState

    data class Error(
        val message: String
    ) : CastScreenState
}