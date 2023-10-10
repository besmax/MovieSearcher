package bes.max.moviesearcher.ui.names

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.FragmentMoviesBinding
import bes.max.moviesearcher.databinding.FragmentNamesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamesFragment : Fragment() {

    private var _binding: FragmentNamesBinding? = null
    private val binding get() = _binding!!
    private val namesViewModel: NamesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
    }


}