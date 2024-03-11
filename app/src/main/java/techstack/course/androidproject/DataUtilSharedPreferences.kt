package techstack.course.androidproject

import kotlinx.coroutines.delay
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
    suspend fun getTeachers(): List<TeacherEntityItem> {
        delay(1000)
        return TeacherDb.getTeacherDao().getAll()
    }

    suspend fun addTeacher(vararg teacher: TeacherEntityItem) {
        TeacherDb.getTeacherDao().insertAll(*teacher)
    }

    suspend fun removeTeacher(teacher: TeacherEntityItem) {
        TeacherDb.getTeacherDao().delete(teacher)
    }
}