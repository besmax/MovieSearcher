package bes.max.moviesearcher.ui.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bes.max.moviesearcher.databinding.FragmentMoviesBinding
import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.ui.main.MainActivityViewModel
import bes.max.moviesearcher.util.debounce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private var adapter: MovieListAdapter? = null
    private var textWatcher: TextWatcher? = null

    private val viewModel: MoviesViewModel by viewModels()
    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var onMovieClickDebounce: (Movie) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onMovieClickDebounce = debounce<Movie>(
            CLICK_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { movie ->
            sharedViewModel.posterUrl.value = movie.image
            sharedViewModel.movieId.value = movie.id
            val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(movie.id)
            findNavController().navigate(action)
        }

        binding.recyclerViewMoviesScreen.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter = MovieListAdapter { movie ->
            onMovieClickDebounce(movie)
        }
        binding.recyclerViewMoviesScreen.adapter = adapter

        viewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                MoviesScreenState.ShowContent -> showContent()
                MoviesScreenState.Loading -> showLoading()
                MoviesScreenState.Error -> showError()
                MoviesScreenState.NotStarted -> showDefaultContent()
            }
        }

        setSearchTextWatcher()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        binding.recyclerViewMoviesScreen.adapter = null
        textWatcher.let { binding.editTextMoviesScreen.removeTextChangedListener(it) }
        _binding = null

    }

    private fun showContent() {
        adapter?.movies = MoviesScreenState.ShowContent.movies
        binding.recyclerViewMoviesScreen.visibility = View.VISIBLE
        binding.progressbarMoviesScreen.visibility = View.GONE
        binding.errorMessageMoviesScreen.visibility = View.GONE
        hideKeyboard()
    }

    private fun showLoading() {
        binding.recyclerViewMoviesScreen.visibility = View.GONE
        binding.progressbarMoviesScreen.visibility = View.VISIBLE
        binding.errorMessageMoviesScreen.visibility = View.GONE
    }

    private fun showDefaultContent() {
        binding.recyclerViewMoviesScreen.visibility = View.GONE
        binding.progressbarMoviesScreen.visibility = View.GONE
        binding.errorMessageMoviesScreen.visibility = View.GONE
    }

    private fun showError() {
        binding.recyclerViewMoviesScreen.visibility = View.GONE
        binding.progressbarMoviesScreen.visibility = View.GONE
        binding.errorMessageMoviesScreen.visibility = View.VISIBLE
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity?.currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    private fun setSearchTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //no need
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    viewModel.searchDebounce(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                //no need
            }

        }
        binding.editTextMoviesScreen.addTextChangedListener(textWatcher)
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 300L
    }

}