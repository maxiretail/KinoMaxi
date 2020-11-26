package com.ministren.kinomaxi.db.fav_film

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ministren.kinomaxi.entity.FavFilm

/**
 * Сущность избранного фильма
 * БД представление
 */
@Entity(
    tableName = "fav_film",
    indices = arrayOf(Index(value = ["film_id"], unique = true))
)
data class DBFavFilm(
    /**
     * Идентификатор
     */
    @PrimaryKey val id: Long?,
    /**
     * Идентификатор фильма
     */
    @ColumnInfo(name = "film_id") val filmId: Long,
    /**
     * Ссылка на постер
     */
    @ColumnInfo(name = "poster_url") val posterUrl: String
)

fun DBFavFilm.toEntity() = FavFilm(
    id = id,
    filmId = filmId,
    posterUrl = posterUrl
)

fun FavFilm.fromEntity() = DBFavFilm(
    id = id,
    filmId = filmId,
    posterUrl = posterUrl
)