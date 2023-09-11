package bes.max.moviesearcher.data

import bes.max.moviesearcher.data.dto.MovieDetailsResponse
import bes.max.moviesearcher.data.dto.MovieDetailsSearchRequest
import bes.max.moviesearcher.data.dto.MovieSearchRequest
import bes.max.moviesearcher.data.dto.MovieSearchResponse
import bes.max.moviesearcher.domain.api.MoviesRepository
import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.models.MovieDetails

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun getMovies(expression: String): List<Movie> {
        val response = networkClient.doRequest(MovieSearchRequest(expression))
        if (response.resultCode == 200) {
            return (response as MovieSearchResponse).results.map {
                Movie(it.id, it.resultType, it.image, it.title, it.description)
            }
        } else {
            return emptyList()
        }
    }

    override fun getMovieDetails(movieId: String): MovieDetails {
        val response = networkClient.doRequest(MovieDetailsSearchRequest(movieId))
        if (response.resultCode == 200) {
            val movieDetails = (response as MovieDetailsResponse)
            return MovieDetails(
                movieDetails.id,
                movieDetails.title,
                movieDetails.imDbRating,
                movieDetails.year,
                movieDetails.countries,
                movieDetails.genres,
                movieDetails.directors,
                movieDetails.writers,
                movieDetails.stars,
                movieDetails.plot
            )
        } else {
            return MovieDetails(
                "id",
                "title",
                "imDbRating",
                "year",
                "countries",
                "genres",
                "directors",
                "writers",
                "stars",
                "plot"
            )
        }
    }


}