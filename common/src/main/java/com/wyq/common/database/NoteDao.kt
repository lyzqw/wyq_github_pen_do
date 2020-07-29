package com.wyq.common.database

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(entity = NoteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table")
    fun findAll(): List<NoteEntity>

    @Delete
    fun delete(vararg noteEntity: NoteEntity)

    @Query("DELETE FROM note_table")
    fun delete()

}