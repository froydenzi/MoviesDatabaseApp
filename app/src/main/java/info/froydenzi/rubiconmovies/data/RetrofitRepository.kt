package info.froydenzi.rubiconmovies.data

import info.froydenzi.rubiconmovies.data.model.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitRepository {

    fun getMovies(): Observable<MovieModel> {
        return RetrofitInstance.apiCall.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSeries(): Observable<SerieModel> {
        return RetrofitInstance.apiCall.getSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSignleMovies(movie_id: Int): Observable<singleMovieModel> {
        return RetrofitInstance.apiCall.getSignleMovies(movie_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSingleSeries(serie_id: Int): Observable<singleSerieModel> {
        return RetrofitInstance.apiCall.getSingleSeries(serie_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSearchQuery(search_item: String): Observable<searchModel> {
        return RetrofitInstance.apiCall.getSearchQuery(search_item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}