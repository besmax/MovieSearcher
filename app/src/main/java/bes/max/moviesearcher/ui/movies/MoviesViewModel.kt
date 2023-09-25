package bes.max.moviesearcher.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _screenState = MutableLiveData<MoviesScreenState>(MoviesScreenState.NotStarted)
    val screenState: LiveData<MoviesScreenState> = _screenState

    private var searchJob: Job? = null
    private var latestSearchText: String? = null


    fun searchDebounce(newSearchText: String) {
        if (newSearchText == latestSearchText) return

        latestSearchText = newSearchText

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            getMovies(newSearchText)
        }
    }

    fun getMovies(userInput: String) {
        _screenState.value = MoviesScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = moviesRepository.getMovies(userInput)
            if (response is Resource.Success && response.data != null) {
                _screenState.postValue(MoviesScreenState.ShowContent.apply {
                    movies = response.data
                })
            }
            if (response is Resource.Error && response.message == "Проверьте подключение к интернету") {
                _screenState.postValue(MoviesScreenState.Error.apply {
                    noInternet =
                        response.message == "Проверьте подключение к интернету"
                })
            }
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

}