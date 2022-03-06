package lt.alexdevstudio.europelanguages.domain.repository

import lt.alexdevstudio.europelanguages.domain.model.Country

interface CountryRepository {
    suspend fun getCountries(region: String): List<Country>
}