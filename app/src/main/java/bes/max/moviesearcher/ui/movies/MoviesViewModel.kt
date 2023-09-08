package bes.max.moviesearcher.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _screenState = MutableLiveData<MoviesScreenState>(MoviesScreenState.NotStarted)
    val screenState: LiveData<MoviesScreenState> = _screenState


    fun getMovies(userInput: String) {
        _screenState.value = MoviesScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val listOfMovies = moviesRepository.getMovies(userInput).toMutableList()
                if(listOfMovies.isNotEmpty()) {
                    _screenState.postValue(MoviesScreenState.ShowContent.apply {
                        movies = listOfMovies
                    })
                } else {
                    _screenState.value = MoviesScreenState.Error
                }
            } catch (e: Exception) {
                _screenState.postValue(MoviesScreenState.Error)
            }
        }
    }

}