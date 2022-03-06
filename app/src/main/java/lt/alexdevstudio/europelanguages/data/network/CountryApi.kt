package lt.alexdevstudio.europelanguages.data.network

import lt.alexdevstudio.europelanguages.data.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("/v3.1/region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String) : List<CountryResponse>
}