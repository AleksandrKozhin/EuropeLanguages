package lt.alexdevstudio.europelanguages.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lt.alexdevstudio.europelanguages.databinding.FragmentLanguagesBinding

@AndroidEntryPoint
class LanguagesFragment : Fragment() {
    private val viewModel: LanguagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLanguagesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvCountries.adapter = LanguagesAdapter()

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getCountriesByRegion()
            binding.swipeContainer.isRefreshing = false
        }

        return binding.root;
    }
}