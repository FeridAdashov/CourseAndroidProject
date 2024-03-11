package techstack.course.androidproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import techstack.course.androidproject.recyclerView.TeacherEntityItem


@Dao
interface TeacherDao {
    @Query("SELECT * FROM teacherentityitem")
    fun getAll(): List<TeacherEntityItem>

    @Query("SELECT * FROM teacherentityitem WHERE id IN (:teacherIds)")
    fun loadAllByIds(teacherIds: IntArray): List<TeacherEntityItem>

    @Query(
        "SELECT * FROM teacherentityitem WHERE name LIKE :name AND " +
                "surname LIKE :surname LIMIT 1"
    )
    fun findByName(name: String, surname: String): TeacherEntityItem

    //TODO Research OnConflictStrategy for next lesson
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg teacherEntityItems: TeacherEntityItem)

    @Delete
    fun delete(teacherEntityItem: TeacherEntityItem)
}