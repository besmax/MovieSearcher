package bes.max.moviesearcher.ui.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import bes.max.moviesearcher.databinding.FragmentCastBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastFragment : Fragment() {

    private var _binding: FragmentCastBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CastViewModel by viewModels()
    private val adapter = ListDelegationAdapter(
        movieCastHeaderDelegate(),
        movieCastPersonDelegate()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.castScreenRecyclerView.adapter = adapter
        binding.castScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.castScreenState.observe(viewLifecycleOwner) {
            when (it) {
                is CastScreenState.Loading -> showLoading()
                is CastScreenState.Content -> showContent(it)
                is CastScreenState.Error -> showError(it)
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.castScreenRecyclerView.visibility = View.GONE
        binding.castScreenMovieTitle.visibility = View.GONE
    }

    private fun showContent(castScreenState: CastScreenState.Content) {
        binding.progressBar.visibility = View.GONE
        binding.castScreenRecyclerView.visibility = View.VISIBLE
        binding.castScreenMovieTitle.visibility = View.VISIBLE
        binding.castScreenMovieTitle.text = castScreenState.fullTitle
        adapter.items = castScreenState.items
        //TODO adapter.submitList(castScreenState.items)
    }

    private fun showError(castScreenState: CastScreenState.Error) {
        binding.progressBar.visibility = View.GONE
        binding.castScreenRecyclerView.visibility = View.GONE
        binding.castScreenMovieTitle.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}