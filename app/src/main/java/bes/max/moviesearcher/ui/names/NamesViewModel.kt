package bes.max.moviesearcher.ui.names

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.moviesearcher.domain.api.NamesRepository
import bes.max.moviesearcher.util.Resource
import bes.max.moviesearcher.util.debounce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NamesViewModel @Inject constructor(
    private val namesRepository: NamesRepository
) : ViewModel() {

    private val _namesScreenState: MutableLiveData<NamesScreenState> =
        MutableLiveData(NamesScreenState.NotStarted)
    val namesScreenState: LiveData<NamesScreenState> = _namesScreenState

    private val onInputSearchDebounce = debounce<String>(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { inputQuery -> getNames(inputQuery) }

    fun searchDebounce(newSearchText: String) {
        onInputSearchDebounce.invoke(newSearchText)
    }

    fun getNames(expression: String) {
        _namesScreenState.value = NamesScreenState.Loading
        viewModelScope.launch {
            namesRepository.getNames(expression).collect() { response ->
                if (response is Resource.Success) {
                    if (!response.data.isNullOrEmpty()) {
                        _namesScreenState.postValue(NamesScreenState.ShowContent(response.data))
                    } else {
                        _namesScreenState.postValue(NamesScreenState.NothingFound)
                    }
                }
                if (response is Resource.Error) {
                    if (response.message == "Internet error") {
                        _namesScreenState.postValue(NamesScreenState.Error(true))
                    } else {
                        Log.d(TAG, "Error in getNames")
                        _namesScreenState.postValue(NamesScreenState.Error(false))
                    }
                }
            }
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val TAG = "NamesViewModel"
    }

}