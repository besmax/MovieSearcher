package bes.max.moviesearcher.ui.details.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import bes.max.moviesearcher.databinding.FragmentAboutBinding
import bes.max.moviesearcher.domain.models.MovieDetails
import bes.max.moviesearcher.ui.details.DetailsFragmentDirections
import bes.max.moviesearcher.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: AboutViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.movieId.value?.let { viewModel.getMovieDetails(it) }
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            bind(it)
        }

        binding.aboutScreenShowCastButton.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToCastFragment(sharedViewModel.movieId.value!!)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bind(movieDetails: MovieDetails) {
        with(binding) {
            aboutScreenTitle.text = movieDetails.title
            aboutScreenRate.text = movieDetails.imDbRating
            aboutScreenCountry.text = movieDetails.countries
            aboutScreenDirector.text = movieDetails.directors
            aboutScreenWriter.text = movieDetails.writers
            aboutScreenActors.text = movieDetails.stars
            aboutScreenPlot.text = movieDetails.plot
        }
    }

    companion object {
        fun newInstance() = AboutFragment()
    }

}