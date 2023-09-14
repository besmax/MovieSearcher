package bes.max.moviesearcher.ui.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.MovieFullCast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val repository: MoviesRepository,
    state: SavedStateHandle
) : ViewModel() {

    private val _castScreenState = MutableLiveData<CastScreenState>(CastScreenState.Loading)
    val castScreenState: LiveData<CastScreenState> = _castScreenState

    init {
        val movieId = state.get<String>("movieId")
        if (movieId != null) getMovieCast(movieId)
    }

    private fun getMovieCast(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val fullCast = repository.getMovieFullCast(movieId)
            _castScreenState.postValue(castToUiState(fullCast))
        }
    }

    private fun castToUiState(cast: MovieFullCast) : CastScreenState {
        val items = buildList<MovieCastRVItem> {
            if (cast.directors.isNotEmpty()) {
                this += MovieCastRVItem.HeaderTitle("Directors")
                this += cast.directors.map { MovieCastRVItem.PersonItem(it) }
            }
            if (cast.writers.isNotEmpty()) {
                this += MovieCastRVItem.HeaderTitle("Writers")
                this += cast.writers.map { MovieCastRVItem.PersonItem(it) }
            }
            if (cast.actors.isNotEmpty()) {
                this += MovieCastRVItem.HeaderTitle("Actors")
                this += cast.actors.map { MovieCastRVItem.PersonItem(it) }
            }
            if (cast.others.isNotEmpty()) {
                this += MovieCastRVItem.HeaderTitle("Others")
                this += cast.others.map { MovieCastRVItem.PersonItem(it) }
            }
        }
        return CastScreenState.Content(cast.fullTitle, items)
    }

}