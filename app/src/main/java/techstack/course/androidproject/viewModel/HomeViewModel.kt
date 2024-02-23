package techstack.course.androidproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


//controller without navigation

interface HomeUIDataListener {
    fun onResult(data: Int)
}

class HomeViewModelFactory(
    private val listener: HomeUIDataListener
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(listener) as T
    }
}

class HomeViewModel(private val listener: HomeUIDataListener) : ViewModel() {
    var data: Int? = null

    fun sum(vararg numbers: Int) {
        data = numbers.sum()

        listener.onResult(data!!)
    }

    fun multiply(vararg numbers: Int) {
        data = numbers.reduce { accumulator, element ->
            accumulator * element
        }

        listener.onResult(data!!)
    }
}