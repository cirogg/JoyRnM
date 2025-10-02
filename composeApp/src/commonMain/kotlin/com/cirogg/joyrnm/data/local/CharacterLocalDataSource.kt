package com.cirogg.joyrnm.data.local

import com.cirogg.rnm.db.CharacterEntity
import com.cirogg.rnm.db.CharacterQueries

class CharacterLocalDataSource(
    private val queries: CharacterQueries
) {
    fun getAll(): List<CharacterEntity> = queries.selectAll().executeAsList()

    fun getById(id: Long): CharacterEntity? = queries.selectById(id).executeAsOneOrNull()

    fun insert(entity: CharacterEntity) {
        queries.insertOrReplace(
            id = entity.id,
            name = entity.name,
            species = entity.species,
            status = entity.status,
            imageUrl = entity.imageUrl,
            originName = entity.originName,
            locationName = entity.locationName,
            originUrl = entity.originUrl,
            locationUrl = entity.locationUrl,
            episodeUrls = entity.episodeUrls
        )
    }

    fun clear() {
        queries.deleteAll()
    }
}