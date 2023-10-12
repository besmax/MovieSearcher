package bes.max.moviesearcher.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()


}