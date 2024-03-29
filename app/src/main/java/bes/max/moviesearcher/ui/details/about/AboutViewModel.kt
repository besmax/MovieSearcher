package bes.max.moviesearcher.ui.details.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.MovieDetails
import bes.max.moviesearcher.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            moviesRepository.getMovieDetails(movieId).collect() { response ->
                if (response is Resource.Success && response.data != null) {
                    _movieDetails.postValue(response.data!!)
                }
            }
        }
    }

}