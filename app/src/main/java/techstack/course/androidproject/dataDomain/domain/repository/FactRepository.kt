package techstack.course.androidproject.dataDomain.domain.repository

import techstack.course.androidproject.dataDomain.domain.entity.FactEntity

interface FactRepository {
    suspend fun getFact(drillDowns: String, measures: String): FactEntity
}