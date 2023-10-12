package bes.max.moviesearcher.ui.names

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.FragmentNamesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamesFragment : Fragment() {

    private var _binding: FragmentNamesBinding? = null
    private val binding get() = _binding!!
    private val namesViewModel: NamesViewModel by viewModels()

    private lateinit var textWatcher: TextWatcher
    private var adapter: PersonListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.personsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = PersonListAdapter()
        binding.personsList.adapter = adapter

        setTextWatcher()

        binding.queryInputLayout.setEndIconOnClickListener {
            binding.queryInput.text?.clear()
            namesViewModel.clearNames()
        }

        namesViewModel.namesScreenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is NamesScreenState.Loading -> showLoading()
                is NamesScreenState.ShowContent -> showContent(state)
                is NamesScreenState.NothingFound -> showNothingFound()
                is NamesScreenState.Error -> showError(state)
                is NamesScreenState.NotStarted -> showNotStarted()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.personsList.adapter = null
        adapter = null
        binding.queryInput.removeTextChangedListener(textWatcher)
    }

    private fun setTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank()) {
                    namesViewModel.searchDebounce(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        }
        binding.queryInput.addTextChangedListener(textWatcher)
    }

    private fun showNotStarted() {
        with(binding) {
            progressBar.visibility = View.GONE
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.VISIBLE
            placeholderMessage.text = getString(R.string.search_not_started)
        }
    }

    private fun showContent(state: NamesScreenState.ShowContent) {
        adapter?.submitList(state.names)
        with(binding) {
            progressBar.visibility = View.GONE
            personsList.visibility = View.VISIBLE
            placeholderMessage.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.GONE
        }
    }

    private fun showError(state: NamesScreenState.Error) {
        with(binding) {
            progressBar.visibility = View.GONE
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.VISIBLE
            placeholderMessage.text = getString(
                if (state.noInternet) R.string.error_no_internet
                else R.string.something_went_wrong
            )
        }
    }

    private fun showNothingFound() {
        with(binding) {
            progressBar.visibility = View.GONE
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.VISIBLE
            placeholderMessage.text = getString(R.string.nothing_found)
        }
    }
}