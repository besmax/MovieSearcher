package bes.max.moviesearcher.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import bes.max.moviesearcher.databinding.FragmentHistoryBinding
import bes.max.moviesearcher.domain.models.Movie

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyViewModel: HistoryViewModel by viewModels()
    private var historyAdapter: HistoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter = HistoryAdapter()

        binding.historyList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.historyList.adapter = historyAdapter
        historyViewModel.fillData()

        historyViewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        historyAdapter = null
        binding.historyList.adapter = null
        _binding = null

    }

    private fun render(state: HistoryState) {
        when (state) {
            is HistoryState.Content -> showContent(state.movies)
            is HistoryState.Empty -> showEmpty(state.message)
            is HistoryState.Loading -> showLoading()
        }
    }

    private fun showLoading() {
        binding.historyList.visibility = View.GONE
        binding.placeholderMessage.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showEmpty(message: String) {
        binding.historyList.visibility = View.GONE
        binding.placeholderMessage.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        binding.placeholderMessage.text = message
    }

    private fun showContent(movies: List<Movie>) {
        binding.historyList.visibility = View.VISIBLE
        binding.placeholderMessage.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        historyAdapter?.movies?.clear()
        historyAdapter?.movies?.addAll(movies)
        historyAdapter?.notifyDataSetChanged()
    }

    companion object {

    }
}