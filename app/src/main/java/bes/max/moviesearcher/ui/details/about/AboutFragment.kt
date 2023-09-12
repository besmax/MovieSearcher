package bes.max.moviesearcher.ui.details.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bes.max.moviesearcher.databinding.FragmentAboutBinding
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
        FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.movieId.value?.let { viewModel.getMovieDetails(it) }

    }

    private fun bind() {
        with(binding) {
            if (viewModel.movieDetails.value != null) {
                aboutScreenRate.text = viewModel.movieDetails.value!!.imDbRating
            }


        }
    }

    companion object {
        fun newInstance() = AboutFragment()
    }

}