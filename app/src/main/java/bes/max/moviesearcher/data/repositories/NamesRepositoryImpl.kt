package bes.max.moviesearcher.data.repositories

import android.util.Log
import bes.max.moviesearcher.data.NetworkClient
import bes.max.moviesearcher.data.dto.requests.NamesSearchRequest
import bes.max.moviesearcher.data.dto.responses.NamesSearchResponse
import bes.max.moviesearcher.data.mappers.PersonDtoMapper
import bes.max.moviesearcher.domain.api.NamesRepository
import bes.max.moviesearcher.domain.models.Person
import bes.max.moviesearcher.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "NamesRepositoryImpl"

class NamesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: PersonDtoMapper
) : NamesRepository {
    override fun getNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequest(NamesSearchRequest(expression))

        Log.d(TAG, "getNames() get response with ${response.resultCode} code")

        when (response.resultCode) {
            -1 -> emit(Resource.Error("Internet error"))
            200 -> {
                val data = (response as NamesSearchResponse).results.map { mapper.map(it) }
                emit(Resource.Success(data))
            }

            else -> {
                emit(Resource.Error("Server error"))
            }
        }
    }
}