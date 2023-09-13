package bes.max.moviesearcher.ui.cast

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.FragmentCastBinding

class CastFragment : Fragment() {

    private var _binding: FragmentCastBinding? = null
    private val binding = _binding!!

    private lateinit var viewModel: CastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CastFragment()
    }

}