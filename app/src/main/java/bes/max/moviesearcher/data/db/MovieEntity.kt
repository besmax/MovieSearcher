package bes.max.moviesearcher.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "movie_name", typeAffinity = ColumnInfo.TEXT)
    val title: String,
    val description: String
)
