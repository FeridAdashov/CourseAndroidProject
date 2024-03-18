package techstack.course.androidproject.recyclerView

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class TeacherEntityItem(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "age") val age: Int,
    @PrimaryKey var id: Int? = null,
) {
    init {
        id = id ?: Random.nextInt(0, 100_000)
    }
}