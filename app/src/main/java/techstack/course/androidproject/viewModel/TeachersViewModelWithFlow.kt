package techstack.course.androidproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import techstack.course.androidproject.DataUtilRoomDatabase
import techstack.course.androidproject.recyclerView.TeacherEntityItem

fun <T> Flow<T>.mutableStateIn(
    scope: CoroutineScope,
    initialValue: T
): MutableStateFlow<T> {
    val flow = MutableStateFlow(initialValue)

    scope.launch {
        this@mutableStateIn.collect(flow)
    }

    return flow
}

class TeachersViewModel : ViewModel() {

    private val _teachersLiveData: MutableStateFlow<TeachersScreenState> =
        DataUtilRoomDatabase.getTeachers().map {
            if (it.isEmpty()) {
                TeachersScreenState.ErrorState("Teachers not found")
            } else {
                TeachersScreenState.ResultState(it)
            }
        }.mutableStateIn(viewModelScope, TeachersScreenState.InitialState)

    val teachersScreenStateLiveData: Flow<TeachersScreenState> = _teachersLiveData


    fun addTeacher(teacher: TeacherEntityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            DataUtilRoomDatabase.addTeacher(teacher)
        }
    }

    fun removeTeacher(teacher: TeacherEntityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            DataUtilRoomDatabase.removeTeacher(teacher)
        }
    }

    fun removeTeacher(teacherId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            DataUtilRoomDatabase.removeTeacher(teacherId)
        }
    }

    //State Machine
    sealed class TeachersScreenState {
        data object InitialState : TeachersScreenState()
        data object LoadingState : TeachersScreenState()
        data class ResultState(val teachers: List<TeacherEntityItem>) : TeachersScreenState()
        data class ErrorState(val message: String) : TeachersScreenState()
    }
}








