package lt.alexdevstudio.europelanguages.data.model

import lt.alexdevstudio.europelanguages.domain.model.Country

data class CountryResponse(
    val capitalInfo: CapitalInfo,
    val languages: Map<String, String>,
    val name: Name
) {
    fun toCountry(): Country = Country(
        name = name.common,
        languages = languages,
        latitude = capitalInfo.latlng[0],
        longitude = capitalInfo.latlng[1]
    )
}