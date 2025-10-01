package com.cirogg.joyrnm.domain.usecase

import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.joyrnm.domain.repository.CharacterRepository


class GetCharactersPageUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Character>> {
        return repository.getCharacters(page)
    }
}