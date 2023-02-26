package com.soten.search.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM queryentity ORDER BY createAt DESC")
    fun findAllByMovieEntity(): Flow<List<QueryEntity>>

    @Query("SELECT * FROM queryentity ORDER BY createAt ASC LIMIT 1")
    fun findByOldestMovieEntity(): QueryEntity?

    @Insert(onConflict = REPLACE)
    fun insertMovieEntity(movieEntity: QueryEntity)
}