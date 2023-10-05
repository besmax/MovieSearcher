package bes.max.moviesearcher.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface MovieDao {

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertNewMovie(movieEntity: MovieEntity)

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertNewMovies(movies: List<MovieEntity>)

    @Update(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movieEntity: MovieEntity)

    @Delete(entity = MovieEntity::class)
    fun deleteMovie(movieEntity: MovieEntity)



}