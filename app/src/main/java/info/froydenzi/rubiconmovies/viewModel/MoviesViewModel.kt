package info.froydenzi.rubiconmovies.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.froydenzi.rubiconmovies.data.RetrofitRepository
import info.froydenzi.rubiconmovies.data.model.MovieModel
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val retrofitRepository = RetrofitRepository()
    var myMovieResponse: MutableLiveData<MovieModel> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            retrofitRepository.getMovies().subscribe({
                myMovieResponse.value = it
            }, {
                Log.d("RESPONSE HANDLER", it.toString())
                myMovieResponse.value = null
            })
        }
    }
}