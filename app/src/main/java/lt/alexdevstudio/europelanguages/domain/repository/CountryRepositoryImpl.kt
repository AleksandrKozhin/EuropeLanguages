package lt.alexdevstudio.europelanguages.domain.repository

import lt.alexdevstudio.europelanguages.data.network.CountryApi
import lt.alexdevstudio.europelanguages.domain.model.Country

class CountryRepositoryImpl(
    private val api: CountryApi
) : CountryRepository {
    override suspend fun getCountries(region: String): List<Country> =
        api.getCountriesByRegion(region).map { it.toCountry() }
}