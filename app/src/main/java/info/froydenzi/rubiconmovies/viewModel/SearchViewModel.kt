package info.froydenzi.rubiconmovies.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.froydenzi.rubiconmovies.data.RetrofitRepository
import info.froydenzi.rubiconmovies.data.model.searchModel
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val retrofitRepository = RetrofitRepository()
    var mySearchCallResponse: MutableLiveData<searchModel> = MutableLiveData()

    fun getSearch(query: String, context: Context?) {

        viewModelScope.launch {
            retrofitRepository.getSearchQuery(query).subscribe({
                mySearchCallResponse.value = it
            }, {
                Log.d("RESPONSE HANDLER", it.toString())
                mySearchCallResponse.value = null
            })
        }
    }
}