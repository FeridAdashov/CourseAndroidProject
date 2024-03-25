package techstack.course.androidproject.dataDomain.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import techstack.course.androidproject.dataDomain.data.remote.responses.FactResponse


interface FactService {

    @GET("data")
    suspend fun getFact(
        @Query("drilldowns") drillDowns: String,
        @Query("measures") measures: String
    ): FactResponse

}