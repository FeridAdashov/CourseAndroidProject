package techstack.course.androidproject.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import techstack.course.androidproject.recyclerView.TeacherEntityItem

class MySharedPreferences private constructor() {
    companion object {
        @Volatile
        private var preferenceManager: SharedPreferences? = null

        fun init(context: Context) {
            synchronized(this) {
                if (preferenceManager == null) {
//                    preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
                    preferenceManager =
                        context.getSharedPreferences(KEY_SHARED_PREFERENCE, Context.MODE_PRIVATE)
                }
            }
        }

        fun addTeacher(teacher: TeacherEntityItem) {
            val currentTeachers = getTeachers().toMutableSet()
            currentTeachers.add(teacher)

            updateTeachers(teachers = currentTeachers)
        }

        fun removeTeacher(teacher: TeacherEntityItem) {
            val currentTeachers = getTeachers().toMutableSet()
            currentTeachers.remove(teacher)

            updateTeachers(teachers = currentTeachers)
        }

        private fun updateTeachers(teachers: Set<TeacherEntityItem>) = preferenceManager?.edit {
            putString(
                KEY_TEACHERS,
                Gson().toJson(teachers)
            )
        }


        fun getTeachers(): Set<TeacherEntityItem> {
            return Gson().fromJson(
                preferenceManager?.getString(KEY_TEACHERS, "[]") ?: "[]",
                object : TypeToken<Set<TeacherEntityItem>>() {}.type
            )
        }


        fun getLang(): String {
            return preferenceManager?.getString(KEY_LANG, "az") ?: "az"
        }

        private const val KEY_SHARED_PREFERENCE = "KEY_SHARED_PREFERENCE"
        private const val KEY_TEACHERS = "KEY_TEACHERS"
        private const val KEY_LANG = "LANG"
    }
}