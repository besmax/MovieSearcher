package bes.max.moviesearcher.ui.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.R
import bes.max.moviesearcher.domain.db.HistoryInteractor
import bes.max.moviesearcher.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyInteractor: HistoryInteractor,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val stateLiveData = MutableLiveData<HistoryState>()

    fun observeState(): LiveData<HistoryState> = stateLiveData

    fun fillData() {
        renderState(HistoryState.Loading)
        viewModelScope.launch {
            historyInteractor
                .historyMovies()
                .collect { movies ->
                    processResult(movies)
                }
        }
    }

    private fun processResult(movies: List<Movie>) {
        if (movies.isEmpty()) {
            renderState(HistoryState.Empty(context.getString(R.string.nothing_searched_yet)))
        } else {
            renderState(HistoryState.Content(movies))
        }
    }

    private fun renderState(state: HistoryState) {
        stateLiveData.postValue(state)
    }
}