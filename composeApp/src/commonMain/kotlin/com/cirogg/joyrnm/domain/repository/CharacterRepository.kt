package com.cirogg.joyrnm.domain.repository

import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<List<Character>>
}