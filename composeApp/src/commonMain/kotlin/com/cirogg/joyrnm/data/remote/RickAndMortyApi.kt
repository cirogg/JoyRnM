package com.cirogg.joyrnm.data.remote

import com.cirogg.joyrnm.data.remote.dto.CharacterDto
import com.cirogg.joyrnm.data.remote.dto.EpisodeDto
import com.cirogg.joyrnm.data.remote.dto.LocationDto
import com.cirogg.joyrnm.data.remote.dto.PagedDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int): PagedDto<CharacterDto>
    suspend fun getLocation(url: String): LocationDto
    suspend fun getEpisode(url: String): EpisodeDto
}

class RickAndMortyApiImpl(
    private val client: HttpClient
) : RickAndMortyApi {
    override suspend fun getCharacters(page: Int): PagedDto<CharacterDto> =
        client.get("https://rickandmortyapi.com/api/character") {
            parameter("page", page)
        }.body()
    override suspend fun getLocation(url: String): LocationDto =
        client.get(url).body()

    override suspend fun getEpisode(url: String): EpisodeDto =
        client.get(url).body()
}