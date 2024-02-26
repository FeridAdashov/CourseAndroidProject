package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentTeachersBinding
import techstack.course.androidproject.recyclerView.TeachersAdapter
import techstack.course.androidproject.viewModel.TeachersViewModel

class TeachersPageFragment : Fragment(R.layout.fragment_teachers) {
    private lateinit var binding: FragmentTeachersBinding

    private val mAdapter: TeachersAdapter by lazy {
        TeachersAdapter().apply {
            setHasStableIds(true)
        }
    }

    private val teachersViewModel: TeachersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTeachersBinding.bind(view)

        binding.teachersRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = mAdapter
        }

        teachersViewModel.teachersScreenStateLiveData.observe(viewLifecycleOwner) {
            binding.progressIndicator.isGone = true
            binding.tvError.isGone = true

            when (it) {
                is TeachersViewModel.TeachersScreenState.ErrorState -> {
                    binding.tvError.isVisible = true
                    binding.tvError.text = it.message
                }

                TeachersViewModel.TeachersScreenState.LoadingState -> {
                    binding.progressIndicator.isVisible = true
                }

                is TeachersViewModel.TeachersScreenState.ResultState -> {
                    mAdapter.models = it.teachers
                }
            }
        }
    }
}