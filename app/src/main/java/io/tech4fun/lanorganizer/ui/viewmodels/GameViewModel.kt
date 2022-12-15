package io.tech4fun.lanorganizer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.tech4fun.lanorganizer.data.repository.MovieRepository
import io.tech4fun.lanorganizer.data.states.MovieUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        private val TAG = "MovieViewModel"
    }

    private val _uiState = MutableStateFlow(listOf<MovieUiState>())

    val uiState: StateFlow<List<MovieUiState>> = _uiState

    private fun getMovies() {
        viewModelScope.launch {
            movieRepository.getMovieApps().collect { list ->
                val listUi = list.map {
                    MovieUiState(it.name, it.appImage)
                }
                _uiState.emit(listUi)
            }
        }
    }

    init {
        getMovies()
    }
}