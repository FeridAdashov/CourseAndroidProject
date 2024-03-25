package techstack.course.androidproject.dataDomain.domain.interactor

import techstack.course.androidproject.dataDomain.domain.entity.FactEntity
import techstack.course.androidproject.dataDomain.domain.repository.FactRepository

class FactInteractor(private val factRepository: FactRepository) {

    suspend fun getFact(drillDowns: String, measures: String): FactEntity {
        return factRepository.getFact(drillDowns, measures)
    }
}