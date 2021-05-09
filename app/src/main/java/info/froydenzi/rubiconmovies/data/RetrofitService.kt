package info.froydenzi.rubiconmovies.data

import info.froydenzi.rubiconmovies.data.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "?api_key=6c5de68e510cf665d1bd14ecf78723cb"
    }

    @GET("movie/top_rated$API_KEY")
    fun getMovies() : Observable<MovieModel>

    @GET("tv/top_rated$API_KEY")
    fun getSeries() : Observable<SerieModel>

    @GET("movie/{movie_id}$API_KEY")
    fun getSignleMovies(@Path("movie_id") movie_id : Int) : Observable<singleMovieModel>

    @GET("tv/{serie_id}$API_KEY")
    fun getSingleSeries(@Path("serie_id") serie_id : Int) : Observable<singleSerieModel>

    @GET("search/multi$API_KEY")
    fun getSearchQuery(@Query("query") search_item: String) : Observable<searchModel>
}