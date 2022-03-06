package lt.alexdevstudio.europelanguages.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lt.alexdevstudio.europelanguages.domain.model.Country
import lt.alexdevstudio.europelanguages.domain.repository.CountryRepository
import javax.inject.Inject

private const val COUNTRY = "Germany"
private const val REGION = "europe"

enum class UiState {
    LOADING,
    ERROR,
    EMPTY,
    DONE
}

@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {
    private val _languages = MutableLiveData<List<String>>()
    val languages: LiveData<List<String>> = _languages

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    init {
        getCountriesByRegion()
    }

    fun getCountriesByRegion() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            try {
                val listOfCountries = countryRepository.getCountries(REGION)
                val germany = findCountry(listOfCountries, COUNTRY)

                if (germany == null) {
                    _languages.value = listOf()
                    _uiState.value = UiState.EMPTY
                } else {
                    _languages.value = getLanguagesByCountry(listOfCountries, germany)
                    _uiState.value = UiState.DONE
                }
            } catch (e: Exception) {
                _uiState.value = UiState.ERROR
                _languages.value = listOf()
            }
        }
    }

    private fun findCountry(listOfCountries: List<Country>, countryName: String): Country? =
        listOfCountries.find { it.name == countryName }

    private fun getLanguagesByCountry(listOfCountries: List<Country>, country: Country): List<String> = listOfCountries
        .asSequence()
        .filter { isFurtherSouthThan(it, country) }
        .map { it.languages.values }
        .flatten()
        .toSet()
        .sorted()
        .toList()

    private fun isFurtherSouthThan(country: Country, target: Country): Boolean =
        country.latitude < target.latitude
}