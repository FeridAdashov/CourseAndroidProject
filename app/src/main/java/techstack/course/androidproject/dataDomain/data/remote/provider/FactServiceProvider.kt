package techstack.course.androidproject.dataDomain.data.remote.provider

import techstack.course.androidproject.dataDomain.data.remote.api.FactService
import techstack.course.androidproject.dataDomain.data.remote.api.RetrofitClient
import techstack.course.androidproject.dataDomain.data.remote.mapper.FactMapper
import techstack.course.androidproject.dataDomain.data.remote.repositoryImpl.FactRepositoryImpl
import techstack.course.androidproject.dataDomain.domain.interactor.FactInteractor

object FactServiceProvider {
    private val factMapper = FactMapper()

    private val factService = RetrofitClient.createService(FactService::class.java)

    private val repositoryImpl = FactRepositoryImpl(factService, factMapper)

    val factInteractor = FactInteractor(repositoryImpl)
}