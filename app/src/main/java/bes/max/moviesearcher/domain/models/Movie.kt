package bes.max.moviesearcher.domain.models

import java.io.FileDescriptor

data class Movie(
    val id: String,
    val ResultType: String,
    val image: String,
    val title: String,
    val description: String
)
