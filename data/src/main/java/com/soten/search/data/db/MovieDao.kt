package com.soten.search.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity ORDER BY createAt DESC")
    fun findAllByMovieEntity(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity ORDER BY createAt ASC LIMIT 1")
    fun findByOldestMovieEntity(): MovieEntity?

    @Insert(onConflict = REPLACE)
    fun insertMovieEntity(movieEntity: MovieEntity)
}