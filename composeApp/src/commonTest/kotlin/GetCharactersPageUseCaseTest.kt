package com.cirogg.joyrnm.domain.usecase

import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.joyrnm.domain.repository.CharacterRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FakeRepositoryForPage : CharacterRepository {
    override suspend fun getCharacterById(id: Long) = TODO()

    override suspend fun getCharacters(page: Int): Result<List<Character>> {
        return if (page == 1) {
            Result.Success(
                listOf(
                    Character(
                        id = 1,
                        name = "Rick Sanchez",
                        species = "Human",
                        status = "Alive",
                        imageUrl = "rick.png",
                        originName = "Earth",
                        originUrl = "",
                        locationName = "Earth",
                        locationUrl = "",
                        episodeUrls = listOf()
                    ),
                    Character(
                        id = 2,
                        name = "Morty Smith",
                        species = "Human",
                        status = "Alive",
                        imageUrl = "morty.png",
                        originName = "Earth",
                        originUrl = "",
                        locationName = "Earth",
                        locationUrl = "",
                        episodeUrls = listOf()
                    )
                )
            )
        } else {
            Result.Error(Exception("Page not found"))
        }
    }

    override suspend fun getLocation(url: String) = TODO()
    override suspend fun getEpisode(url: String) = TODO()
}

class GetCharactersPageUseCaseTest {

    private val useCase = GetCharactersPageUseCase(FakeRepositoryForPage())

    @Test
    fun `should return characters list when page exists`() = runTest {
        val result = useCase(1)

        assertTrue(result is Result.Success)
        assertEquals(2, result.data.size)
        assertEquals("Rick Sanchez", result.data[0].name)
    }

    @Test
    fun `should return error when page does not exist`() = runTest {
        val result = useCase(99)

        assertTrue(result is Result.Error)
    }
}