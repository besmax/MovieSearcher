package bes.max.moviesearcher.ui.names

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    private lateinit var textWatcher: TextWatcher


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextWatcher()

        namesViewModel.namesScreenState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is NamesScreenState.Loading -> showLoading()
                is NamesScreenState.ShowContent -> showContent()
                is NamesScreenState.NothingFound -> showNothingFound()
                is NamesScreenState.Error -> showError()
                is NamesScreenState.NotStarted -> showNotStarted()
            }
        }

    }

    private fun setTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrBlank()) {
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
            //TODO add string res
        }
    }

    private fun showContent() {
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

    private fun showError() {
        with(binding) {
            progressBar.visibility = View.GONE
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.VISIBLE
            //TODO add string res
        }
    }

    private fun showNothingFound() {
        with(binding) {
            progressBar.visibility = View.GONE
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.VISIBLE
            //TODO add string res
        }
    }
}