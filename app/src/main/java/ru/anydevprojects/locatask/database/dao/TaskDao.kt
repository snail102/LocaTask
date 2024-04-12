package ru.anydevprojects.locatask.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.locatask.database.models.TaskEntity

@Dao
interface TaskDao {
    @Insert
    suspend fun insertAll(tasks: List<TaskEntity>)

    @Insert
    suspend fun insert(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Query("SELECT * FROM taskentity WHERE id IN (:taskId)")
    suspend fun getTaskById(taskId: String): TaskEntity

    @Query("SELECT * FROM taskentity")
    fun getAllTaskFlow(): Flow<List<TaskEntity>>

    @Query("DELETE FROM taskentity WHERE id IN (:taskId)")
    suspend fun deleteById(taskId: String)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)
}
