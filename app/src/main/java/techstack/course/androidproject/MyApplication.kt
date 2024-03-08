package techstack.course.androidproject

import android.app.Application
import techstack.course.androidproject.sharedPreferences.MySharedPreferences

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(applicationContext)
    }
}