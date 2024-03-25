package techstack.course.androidproject.dataDomain.data.remote.mapper

import techstack.course.androidproject.dataDomain.data.remote.responses.FactResponse
import techstack.course.androidproject.dataDomain.domain.entity.FactEntity

class FactMapper {
    fun toFactEntity(factResponse: FactResponse): FactEntity {
        return FactEntity(
            data = factResponse.data?.map {
                FactEntity.Datum(
                    idNation = it.idNation,
                    nation = it.nation,
                    idYear = it.idYear,
                    year = it.year,
                    population = it.population,
                    slugNation = it.slugNation,
                )
            },
            source = factResponse.source?.map {
                FactEntity.Source(
                    measures = it.measures,
                    name = it.name,
                    annotations = FactEntity.Annotations(
                        sourceName = it.annotations?.sourceName,
                        sourceDescription = it.annotations?.sourceDescription,
                        datasetName = it.annotations?.datasetName,
                        datasetLink = it.annotations?.datasetLink,
                        tableId = it.annotations?.tableId,
                        topic = it.annotations?.topic,
                        subTopic = it.annotations?.subTopic,
                    )
                )
            }
        )
    }
}


/** Write a mapper to change below classes
 *
 * data class(name: String, surname: String)
 * data class(fullName: String)
 */