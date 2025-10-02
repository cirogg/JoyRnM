package com.cirogg.joyrnm.data.repository

import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.data.local.CharacterLocalDataSource
import com.cirogg.joyrnm.data.local.toEntity
import com.cirogg.joyrnm.data.local.toModel
import com.cirogg.joyrnm.data.remote.RickAndMortyApi
import com.cirogg.joyrnm.data.remote.dto.EpisodeDto
import com.cirogg.joyrnm.data.remote.dto.LocationDto
import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.joyrnm.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext


class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
    private val local: CharacterLocalDataSource
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Result<List<Character>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCharacters(page)

            if (response.results.isEmpty()) {
                return@withContext Result.Success(emptyList())
            }

            val items = response.results.map { dto ->
                Character(
                    id = dto.id.toLong(),
                    name = dto.name,
                    species = dto.species,
                    status = dto.status,
                    imageUrl = dto.image,
                    originName = dto.origin.name,
                    originUrl = dto.origin.url,
                    locationName = dto.location.name,
                    locationUrl = dto.location.url,
                    episodeUrls = dto.episode
                )
            }

            items.forEach { character ->
                local.insert(character.toEntity())
            }

            Result.Success(items)

        } catch (t: Throwable) {
            if (page == 1) {
                val cached = local.getAll().map { it.toModel() }
                if (cached.isNotEmpty()) {
                    Result.Success(cached)
                } else {
                    Result.Error(t)
                }
            } else {
                Result.Success(emptyList())
            }
        }
    }

    override suspend fun getCharacterById(id: Long): Character? {
        return local.getById(id)?.toModel()
    }

    override suspend fun getLocation(url: String): Result<LocationDto> = try {
        Result.Success(api.getLocation(url))
    } catch (t: Throwable) {
        Result.Error(t)
    }

    override suspend fun getEpisode(url: String): Result<EpisodeDto> = try {
        Result.Success(api.getEpisode(url))
    } catch (t: Throwable) {
        Result.Error(t)
    }
}