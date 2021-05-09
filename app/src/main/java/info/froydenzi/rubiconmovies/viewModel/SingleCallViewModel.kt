package info.froydenzi.rubiconmovies.viewModel

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.froydenzi.rubiconmovies.data.RetrofitRepository
import info.froydenzi.rubiconmovies.data.model.singleMovieModel
import info.froydenzi.rubiconmovies.data.model.singleSerieModel
import kotlinx.coroutines.launch

class SingleCallViewModel : ViewModel() {

    private val retrofitRepository = RetrofitRepository()
    var myMovieCallResponse: MutableLiveData<singleMovieModel> = MutableLiveData()
    var mySerieCallResponse: MutableLiveData<singleSerieModel> = MutableLiveData()

    fun getCall(id: Int, type: String?, context: Context?) {
        if (type.equals("movie")) {
            viewModelScope.launch {
                retrofitRepository.getSignleMovies(id).subscribe({
                    myMovieCallResponse.value = it
                }, {
                    Log.d("RESPONSE HANDLER", it.toString())
                    myMovieCallResponse.value = null
                })
            }
            (context as AppCompatActivity?)!!.supportActionBar!!.title = "MOVIE Info"
        } else if (type.equals("serie")) {
            viewModelScope.launch {
                retrofitRepository.getSingleSeries(id).subscribe({
                    mySerieCallResponse.value = it
                }, {
                    Log.d("RESPONSE HANDLER", it.toString())
                    mySerieCallResponse.value = null
                })
            }
            (context as AppCompatActivity?)!!.supportActionBar!!.title = "TV SHOW Info"
        }
    }

}