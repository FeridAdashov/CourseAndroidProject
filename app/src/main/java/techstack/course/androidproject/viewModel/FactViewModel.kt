package techstack.course.androidproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import techstack.course.androidproject.dataDomain.data.remote.provider.FactServiceProvider
import techstack.course.androidproject.dataDomain.domain.entity.FactEntity
import techstack.course.androidproject.dataDomain.domain.interactor.FactInteractor

class FactViewModel(private val factInteractor: FactInteractor) : ViewModel() {

    private val _factFlow = MutableStateFlow<FactScreenState>(FactScreenState.InitialState)
    val factFlow: StateFlow<FactScreenState> = _factFlow


    fun getFact(drillDowns: String, measures: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _factFlow.emit(FactScreenState.LoadingState)

            val data = factInteractor.getFact(drillDowns, measures)

            if (data.data.isNullOrEmpty() || data.source.isNullOrEmpty())
                _factFlow.emit(FactScreenState.ErrorState("data is null or empty"))
            else
                _factFlow.emit(FactScreenState.ResultState(data))
        }
    }
}


sealed class FactScreenState {
    data object InitialState : FactScreenState()
    data object LoadingState : FactScreenState()
    data class ResultState(val fact: FactEntity) : FactScreenState()
    data class ErrorState(val message: String) : FactScreenState()
}


fun main(): Unit = runBlocking {

    CoroutineScope(Dispatchers.IO).launch {
        val viewModel = FactViewModel(FactServiceProvider.factInteractor)

        viewModel.getFact("Nation", "Population")

        viewModel.factFlow.collectLatest {
            println(it.toString())
        }

    }

    delay(5000)
}