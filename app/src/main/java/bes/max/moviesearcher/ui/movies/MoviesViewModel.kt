package bes.max.moviesearcher.ui.movies

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

    private fun getMovies(userInput: String) {
        viewModelScope.launch(Dispatchers.IO) {
            listOfMovies = movieRepository.getMovies(userInput).toMutableList()
        }
    }

}