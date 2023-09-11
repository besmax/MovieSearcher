package bes.max.moviesearcher.ui.details.about

import androidx.lifecycle.ViewModel
import bes.max.moviesearcher.domain.api.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    //private val movieId: String,
    private val moviesRepository: MoviesRepository
) : ViewModel() {



}