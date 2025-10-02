package com.cirogg.joyrnm.domain.usecase

import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.data.remote.dto.EpisodeDto
import com.cirogg.joyrnm.data.remote.dto.LocationDto
import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.joyrnm.domain.repository.CharacterRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FakeRepositorySuccess : CharacterRepository {
    override suspend fun getCharacterById(id: Long): Character? {
        return Character(
            id = id,
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            imageUrl = "https://rick.png",
            originName = "Earth",
            originUrl = "",
            locationName = "Earth",
            locationUrl = "",
            episodeUrls = listOf()
        )
    }

    override suspend fun getCharacters(page: Int): Result<List<Character>> {
        TODO("Not needed for this test")
    }

    override suspend fun getLocation(url: String): Result<LocationDto> {
        return Result.Success(
            LocationDto(1, "Earth", "Planet", "C-137", listOf("a", "n"))
        )
    }

    override suspend fun getEpisode(url: String): Result<EpisodeDto> {
        return Result.Success(
            EpisodeDto(1, "Pilot", "Dec 2, 2013", "S01E01", listOf())
        )
    }
}

class FakeRepositoryCharacterNotFound : CharacterRepository {
    override suspend fun getCharacterById(id: Long): Character? = null
    override suspend fun getCharacters(page: Int) = TODO()
    override suspend fun getLocation(url: String) = TODO()
    override suspend fun getEpisode(url: String) = TODO()
}

class FakeRepositoryLocationFail : CharacterRepository {
    override suspend fun getCharacterById(id: Long): Character? {
        return Character(
            id = id,
            name = "Morty",
            species = "Human",
            status = "Alive",
            imageUrl = "https://morty.png",
            originName = "Earth",
            originUrl = "",
            locationName = "",
            locationUrl = "https://bad-location",
            episodeUrls = emptyList()
        )
    }

    override suspend fun getCharacters(page: Int) = TODO()
    override suspend fun getLocation(url: String): Result<LocationDto> {
        return Result.Error(Exception("Location service down"))
    }
    override suspend fun getEpisode(url: String) = TODO()
}


class GetCharacterDetailUseCaseTest {

    @Test
    fun `should return character detail when character exists`() = runTest {
        val useCase = GetCharacterDetailUseCase(FakeRepositorySuccess())
        val result = useCase(1L)

        assertTrue(result is Result.Success)
        assertEquals("Rick Sanchez", result.data.character.name)
    }

    @Test
    fun `should return error when character does not exist`() = runTest {
        val useCase = GetCharacterDetailUseCase(FakeRepositoryCharacterNotFound())
        val result = useCase(99L)

        assertTrue(result is Result.Error)
    }

    @Test
    fun `should return character even if location fails`() = runTest {
        val useCase = GetCharacterDetailUseCase(FakeRepositoryLocationFail())
        val result = useCase(2L)

        assertTrue(result is Result.Success)
        assertEquals("Morty", result.data.character.name)
    }
}