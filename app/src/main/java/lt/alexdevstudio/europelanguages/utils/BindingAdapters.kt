package lt.alexdevstudio.europelanguages.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import lt.alexdevstudio.europelanguages.R
import lt.alexdevstudio.europelanguages.ui.LanguagesAdapter
import lt.alexdevstudio.europelanguages.ui.UiState

@BindingAdapter("data")
fun RecyclerView.data(data: List<String>?) {
    (adapter as LanguagesAdapter).submitList(data)
}

@BindingAdapter("uiState")
fun ImageView.uiState(uiState: UiState?) {
    when(uiState) {
        UiState.ERROR -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.ic_connection_error)
        }
        UiState.EMPTY -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.ic_empty_list)
        }
        else -> visibility = View.GONE
    }
}

@BindingAdapter("uiState")
fun ProgressBar.uiState(uiState: UiState?) {
    visibility = when(uiState) {
        UiState.LOADING -> View.VISIBLE
        else -> View.GONE
    }
}