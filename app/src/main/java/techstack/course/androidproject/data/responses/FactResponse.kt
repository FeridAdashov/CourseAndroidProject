package techstack.course.androidproject.data.responses

import com.google.gson.annotations.SerializedName


data class FactResponse(
    val data: List<Datum>? = null,
    val source: List<Source>? = null
) {
    data class Datum(
        @SerializedName("ID Nation")
        val idNation: String? = null,

        @SerializedName("Nation")
        val nation: String? = null,

        @SerializedName("ID Year")
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
        val tableid: String? = null,
        val topic: String? = null,
        val subtopic: String? = null
    )
}

