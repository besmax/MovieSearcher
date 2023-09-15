package bes.max.moviesearcher.ui.cast

import bes.max.moviesearcher.core.ui.RVItem

sealed interface CastScreenState {

    object Loading : CastScreenState

    data class Content(
        val fullTitle: String,
        val items: List<RVItem>
    ) : CastScreenState

    data class Error(
        val message: String
    ) : CastScreenState
}