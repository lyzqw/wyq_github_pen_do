package com.wyq.common.database

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(entity = NoteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table where type IN (:typeList) ORDER BY create_date DESC LIMIT :loadSize OFFSET :pageSize")
    fun findAll(typeList: List<Int>, pageSize: Int, loadSize: Int): List<NoteEntity>

    @Delete
    fun delete(vararg noteEntity: NoteEntity)

    @Query("DELETE FROM note_table")
    fun delete()

    @Query("UPDATE note_table SET content = :content WHERE id= :noteId")
    fun updateNote(noteId: Int, content: String?)

    @Query("SELECT * FROM note_table ORDER BY create_date DESC limit 1")
    fun findLatestNote(): NoteEntity?

}