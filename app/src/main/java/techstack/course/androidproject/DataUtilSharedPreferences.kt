package techstack.course.androidproject

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import techstack.course.androidproject.recyclerView.TeacherEntityItem
import techstack.course.androidproject.room.TeacherDb
import techstack.course.androidproject.sharedPreferences.MySharedPreferences

object DataUtilSharedPreferences {
    suspend fun getTeachers(): List<TeacherEntityItem> {
        delay(1000)
        return MySharedPreferences.getTeachers().toList()
    }

    fun addTeacher(teacher: TeacherEntityItem) {
        MySharedPreferences.addTeacher(teacher)
    }

    fun removeTeacher(teacher: TeacherEntityItem) {
        MySharedPreferences.removeTeacher(teacher)
    }
}


object DataUtilRoomDatabase {
    fun getTeachers(): Flow<List<TeacherEntityItem>> =
        TeacherDb.getTeacherDao().getAll()

    suspend fun addTeacher(vararg teacher: TeacherEntityItem) {
        TeacherDb.getTeacherDao().insertAll(*teacher)
    }

    suspend fun removeTeacher(teacher: TeacherEntityItem) {
        TeacherDb.getTeacherDao().delete(teacher)
    }

    suspend fun removeTeacher(teacherId: Int) {
        TeacherDb.getTeacherDao().delete(teacherId)
    }
}