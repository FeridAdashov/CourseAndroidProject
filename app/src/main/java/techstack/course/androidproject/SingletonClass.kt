package techstack.course.androidproject

import android.util.Log

class SingletonClass private constructor(var x: Int) {
    companion object {
        @Volatile
        private var _INSTANCE: SingletonClass? = null

        fun getInstance(): SingletonClass {
            synchronized(this) {
                if (_INSTANCE == null) {
                    Log.d("SSSSSSS", "SINGLETON")
                    _INSTANCE = SingletonClass(0)
                }
            }

            return _INSTANCE!!
        }
    }
}