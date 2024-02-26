package techstack.course.androidproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import techstack.course.androidproject.DataUtil
import techstack.course.androidproject.recyclerView.TeacherEntityItem

class TeachersViewModel : ViewModel() {

    private val _teachersLiveData = MutableLiveData<TeachersScreenState>()
    val teachersScreenStateLiveData: LiveData<TeachersScreenState> = _teachersLiveData

    init {
        getTeachers()
    }

    fun getTeachers() {
        _teachersLiveData.value = TeachersScreenState.LoadingState

        viewModelScope.launch(Dispatchers.IO) {
            val teachers = DataUtil.getTeachers()

            withContext(Dispatchers.Main) {
                if (teachers.isEmpty()) {
                    _teachersLiveData.value = TeachersScreenState.ErrorState("Teachers not found")
                } else {
                    _teachersLiveData.value = TeachersScreenState.ResultState(teachers)
                }
            }
        }
    }

    sealed class TeachersScreenState {
        data object LoadingState : TeachersScreenState()
        data class ResultState(val teachers: List<TeacherEntityItem>) : TeachersScreenState()
        data class ErrorState(val message: String) : TeachersScreenState()
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










