package techstack.course.androidproject.dataDomain.domain.entity

data class FactEntity(
    val data: List<Datum>? = null,
    val source: List<Source>? = null
) {
    data class Datum(
        val idNation: String? = null,
        val nation: String? = null,
        val idYear: Int? = null,
        val year: String? = null,
        val population: Int? = null,
        val slugNation: String? = null
    )

    data class Source(
        val measures: List<String>? = null,
        val annotations: Annotations? = null,
        val name: String? = null,
    )

    data class Annotations(
        val sourceName: String? = null,
        val sourceDescription: String? = null,
        val datasetName: String? = null,
        val datasetLink: String? = null,
        val tableId: String? = null,
        val topic: String? = null,
        val subTopic: String? = null
    )
}