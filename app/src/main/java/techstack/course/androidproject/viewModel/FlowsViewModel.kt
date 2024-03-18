package techstack.course.androidproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowsViewModel : ViewModel() {

    private val _liveData = MutableLiveData("Hello World")
    val livedata: LiveData<String> = _liveData

    val mFlow = flow {
        repeat(5) {
            emit("${it + 1}")
            delay(1000L)
        }
    }

    private val _stateFlow = mFlow.mutableStateIn(viewModelScope, "")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()


    fun triggerLiveData() {
        _liveData.value = "LiveData"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow"
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }
}








