package lt.alexdevstudio.europelanguages.data.model

data class CountryResponse(
    val capitalInfo: CapitalInfo,
    val languages: Map<String, String>,
    val name: Name
)