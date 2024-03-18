package techstack.course.androidproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import techstack.course.androidproject.recyclerView.TeacherEntityItem


@Dao
interface TeacherDao {
    @Query("SELECT * FROM teacherentityitem")
    fun getAll(): Flow<List<TeacherEntityItem>>

    //TODO Research OnConflictStrategy for next lesson
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg teacherEntityItems: TeacherEntityItem)

    @Delete
    suspend fun delete(teacherEntityItem: TeacherEntityItem)

    @Query("DELETE FROM teacherentityitem WHERE id = :teacherId")
    suspend fun delete(teacherId: Int)
}