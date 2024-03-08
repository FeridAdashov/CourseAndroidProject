package techstack.course.androidproject

import kotlinx.coroutines.delay
import techstack.course.androidproject.recyclerView.TeacherEntityItem
import techstack.course.androidproject.sharedPreferences.MySharedPreferences

object DataUtil {
//    suspend fun getTeachers(hasInternet: Boolean = true): List<TeacherEntityItem> {
//        delay(1000)
//
//        return if (hasInternet) getTeachersFromService() else MySharedPreferences.getTeachers().toList()
//    }

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