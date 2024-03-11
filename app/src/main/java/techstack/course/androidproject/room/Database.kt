package techstack.course.androidproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import techstack.course.androidproject.recyclerView.TeacherEntityItem

@Database(entities = [TeacherEntityItem::class], version = 1)
abstract class TeacherDatabase : RoomDatabase() {
    abstract fun teacherDao(): TeacherDao
}


class TeacherDb {
    companion object {
        @Volatile
        private var db: TeacherDatabase? = null

        fun getTeacherDao() = db!!.teacherDao()

        fun init(context: Context) {
            synchronized(this) {
                if (db == null) {
                    db = Room.databaseBuilder(
                        context,
                        TeacherDatabase::class.java, "database-name"
                    ).build()
                }
            }
        }
    }
}