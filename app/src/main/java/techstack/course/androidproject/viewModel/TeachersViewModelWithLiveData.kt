//package techstack.course.androidproject.viewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MediatorLiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.map
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import techstack.course.androidproject.DataUtilRoomDatabase
//import techstack.course.androidproject.recyclerView.TeacherEntityItem
//
///**
// * Transforms a [LiveData] into [MutableLiveData]
// *
// * @param T type
// * @return [MutableLiveData] emitting the same values
// */
//fun <T> LiveData<T>.toMutableLiveData(): MutableLiveData<T> {
//    val mediatorLiveData = MediatorLiveData<T>()
//    mediatorLiveData.addSource(this) {
//        mediatorLiveData.value = it
//    }
//    return mediatorLiveData
//}
//
//class TeachersViewModel : ViewModel() {
//
//    private val _teachersLiveData: MutableLiveData<TeachersScreenState> =
//        DataUtilRoomDatabase.getTeachers().map {
//            if (it.isEmpty()) {
//                TeachersScreenState.ErrorState("Teachers not found")
//            } else {
//                TeachersScreenState.ResultState(it)
//            }
//        }.toMutableLiveData()
//
//    val teachersScreenStateLiveData: LiveData<TeachersScreenState> = _teachersLiveData
//
//    fun addTeacher(teacher: TeacherEntityItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            DataUtilRoomDatabase.addTeacher(teacher)
//        }
//    }
//
//    fun removeTeacher(teacher: TeacherEntityItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            DataUtilRoomDatabase.removeTeacher(teacher)
//        }
//    }
//
//    //State Machine
//    sealed class TeachersScreenState {
//        data object LoadingState : TeachersScreenState()
//        data class ResultState(val teachers: List<TeacherEntityItem>) : TeachersScreenState()
//        data class ErrorState(val message: String) : TeachersScreenState()
//    }
//}
//
//
//
//
//
//
//
