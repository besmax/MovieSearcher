package bes.max.moviesearcher.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.ui.movies.MoviesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _screenState = MutableLiveData<MoviesScreenState>(MoviesScreenState.NotStarted)
    val screenState: LiveData<MoviesScreenState> = _screenState

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

}