package bes.max.moviesearcher.ui.cast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    var movieId: String? = null

    init {
        movieId = state.get<String>("movieId")
    }

    private fun getMovieCast(movieId: String) {
        viewModelScope
    }

}