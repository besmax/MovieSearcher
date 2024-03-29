package bes.max.moviesearcher.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.util.Resource
import bes.max.moviesearcher.util.debounce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _screenState = MutableLiveData<MoviesScreenState>(MoviesScreenState.NotStarted)
    val screenState: LiveData<MoviesScreenState> = _screenState

    private val onInputSearchDebounce = debounce<String>(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { inputQuery ->
        getMovies(inputQuery)
    }


    fun searchDebounce(newSearchText: String) {
        onInputSearchDebounce.invoke(newSearchText)
    }

    fun getMovies(userInput: String) {
        _screenState.value = MoviesScreenState.Loading
        viewModelScope.launch {
            moviesRepository.getMovies(userInput).collect() { response ->
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
    }

    fun clearMovies() {
        _screenState.value = MoviesScreenState.NotStarted
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

}