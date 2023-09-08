package bes.max.moviesearcher.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import bes.max.moviesearcher.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val adapter = MovieListAdapter()
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMoviesScreen.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewMoviesScreen.adapter = adapter

        viewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                MoviesScreenState.ShowContent -> showContent()
                MoviesScreenState.Loading -> showLoading()
                MoviesScreenState.Error -> showError()
                MoviesScreenState.NotStarted -> showDefaultContent()
            }
        }

        binding.buttonMoviesScreen.setOnClickListener {
            if (binding.editTextMoviesScreen.text?.isNotEmpty() == true) {
                viewModel.getMovies(binding.editTextMoviesScreen.text.toString())
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showContent() {
        adapter.movies = MoviesScreenState.ShowContent.movies
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

}