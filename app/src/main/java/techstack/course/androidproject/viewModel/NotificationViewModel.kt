package techstack.course.androidproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationViewModel : ViewModel() {

    private val _timerLiveData = MutableLiveData<Int>()
    val timerLiveData: LiveData<Int> = _timerLiveData

    private var timer = 0

    init {
        start()
    }

    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                withContext(Dispatchers.Main) {
                    _timerLiveData.value = timer++
                }
                delay(1000)
            }
        }
    }
}

/**
 *         1. Teacher class
 *         2. name, surname, age
 *         3. recyclerview
 *         4. fake function to get teachers list
 *         5. viewModel
 *         6. livedata
 *         7. average of ages
 */










