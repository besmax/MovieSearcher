package bes.max.moviesearcher.ui.movies

import bes.max.moviesearcher.domain.models.Movie

sealed interface MoviesScreenState {
    object NotStarted: MoviesScreenState

    object ShowContent: MoviesScreenState {
        var movies: List<Movie> = emptyList()
    }

    object Loading: MoviesScreenState

    object Error: MoviesScreenState {
        var noInternet = false
    }
}