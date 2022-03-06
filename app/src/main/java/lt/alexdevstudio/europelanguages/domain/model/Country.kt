package lt.alexdevstudio.europelanguages.domain.model

data class Country(
    val name: String,
    val languages: Map<String, String>,
    val latitude: Double,
    val longitude: Double
)