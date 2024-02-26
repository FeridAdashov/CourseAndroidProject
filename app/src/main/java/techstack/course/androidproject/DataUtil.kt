package techstack.course.androidproject

import kotlinx.coroutines.delay
import techstack.course.androidproject.recyclerView.TeacherEntityItem

object DataUtil {
    suspend fun getTeachers(): List<TeacherEntityItem> {
        delay(2000)

        return List(20) {
            TeacherEntityItem("Name: $it", "Surname: $it", 18)
        }
    }
}