package bes.max.moviesearcher.data

import bes.max.moviesearcher.data.dto.responses.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
}