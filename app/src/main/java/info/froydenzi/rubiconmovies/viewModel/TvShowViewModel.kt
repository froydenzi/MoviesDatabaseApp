package info.froydenzi.rubiconmovies.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.froydenzi.rubiconmovies.data.RetrofitRepository
import info.froydenzi.rubiconmovies.data.model.SerieModel
import kotlinx.coroutines.launch

class TvShowViewModel : ViewModel() {

    private val retrofitRepository = RetrofitRepository()
    var mySerieResponse: MutableLiveData<SerieModel> = MutableLiveData()

    fun getSeries() {
        viewModelScope.launch {
            retrofitRepository.getSeries().subscribe({
                mySerieResponse.value = it
            }, {
                Log.d("RESPONSE HANDLER",it.toString())
                mySerieResponse.value = null
            })
        }
    }

}