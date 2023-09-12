package bes.max.moviesearcher.ui.details.poster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.FragmentMoviesBinding
import bes.max.moviesearcher.databinding.FragmentPosterBinding
import bes.max.moviesearcher.ui.main.MainActivityViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PosterFragment : Fragment() {

    private var _binding: FragmentPosterBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: PosterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPoster()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPoster() {
        Glide.with(binding.root)
            .load(sharedViewModel.posterUrl.value)
            .into(binding.posterScreenPoster)

    }

    companion object {
        fun newInstance() = PosterFragment()
    }
}