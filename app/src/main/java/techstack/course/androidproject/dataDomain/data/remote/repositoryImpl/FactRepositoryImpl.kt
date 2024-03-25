package techstack.course.androidproject.dataDomain.data.remote.repositoryImpl

import techstack.course.androidproject.dataDomain.data.remote.api.FactService
import techstack.course.androidproject.dataDomain.data.remote.mapper.FactMapper
import techstack.course.androidproject.dataDomain.domain.entity.FactEntity
import techstack.course.androidproject.dataDomain.domain.repository.FactRepository

class FactRepositoryImpl(private val factService: FactService, private val mapper: FactMapper) :
    FactRepository {
    override suspend fun getFact(drillDowns: String, measures: String): FactEntity {
        val response = factService.getFact(drillDowns, measures)
        return mapper.toFactEntity(response)
    }
}