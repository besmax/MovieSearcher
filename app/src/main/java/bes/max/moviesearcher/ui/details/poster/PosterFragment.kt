package bes.max.moviesearcher.ui.details.poster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.FragmentMoviesBinding
import bes.max.moviesearcher.databinding.FragmentPosterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PosterFragment : Fragment() {

    private var _binding: FragmentPosterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}