package techstack.course.androidproject.dataDomain.data.remote.responses

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

        @SerializedName("year")
        val year: String? = null,

        @SerializedName("population")
        val population: Int? = null,

        @SerializedName("slugNation")
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

