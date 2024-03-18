//package techstack.course.androidproject.viewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import techstack.course.androidproject.DataUtilRoomDatabase
//import techstack.course.androidproject.recyclerView.TeacherEntityItem
//
//class TeachersViewModel : ViewModel() {
//
//    private val _teachersLiveData = MutableLiveData<TeachersScreenState>()
//    val teachersScreenStateLiveData: LiveData<TeachersScreenState> = _teachersLiveData
//
//    init {
//        getTeachers()
//    }
//
//    fun getTeachers() {
//        _teachersLiveData.value = TeachersScreenState.LoadingState
//
//        viewModelScope.launch(Dispatchers.IO) {
//            //Room database
//            val teachers = DataUtilRoomDatabase.getTeachers()
//
//            withContext(Dispatchers.Main) {
//                if (teachers.isEmpty()) {
//                    _teachersLiveData.value = TeachersScreenState.ErrorState("Teachers not found")
//                } else {
//                    _teachersLiveData.value = TeachersScreenState.ResultState(teachers)
//                }
//            }
//        }
//    }
//
//    fun addTeacher(teacher: TeacherEntityItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            DataUtilRoomDatabase.addTeacher(teacher)
//
//            withContext(Dispatchers.Main) {
//                getTeachers()
//            }
//        }
//    }
//
//    fun removeTeacher(teacher: TeacherEntityItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            DataUtilRoomDatabase.removeTeacher(teacher)
//
//            withContext(Dispatchers.Main) {
//                getTeachers()
//            }
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
//
