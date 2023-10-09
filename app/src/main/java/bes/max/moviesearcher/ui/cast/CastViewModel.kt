package bes.max.moviesearcher.ui.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.MovieFullCast
import bes.max.moviesearcher.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
        viewModelScope.launch {
            repository.getMovieFullCast(movieId).collect() { response ->
                if (response is Resource.Success && response.data != null) {
                    _castScreenState.postValue(castToUiState(response.data))
                }
                if (response is Resource.Error && response.message != null) {
                    _castScreenState.postValue(CastScreenState.Error(response.message))
                }
            }
        }
    }

    private fun castToUiState(cast: MovieFullCast): CastScreenState {
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