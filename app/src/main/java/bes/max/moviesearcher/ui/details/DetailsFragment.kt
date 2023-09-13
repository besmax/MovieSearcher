package bes.max.moviesearcher.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bes.max.moviesearcher.R
import bes.max.moviesearcher.databinding.FragmentDetailsBinding
import bes.max.moviesearcher.ui.main.MainActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsScreenViewpager.adapter = DetailsViewPagerAdapter(childFragmentManager, lifecycle)
        tabMediator = TabLayoutMediator(
            binding.detailsScreenTablayout,
            binding.detailsScreenViewpager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_poster)
                1 -> tab.text = getString(R.string.tab_about)
            }
        }
        tabMediator.attach()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        tabMediator.detach()
    }

}