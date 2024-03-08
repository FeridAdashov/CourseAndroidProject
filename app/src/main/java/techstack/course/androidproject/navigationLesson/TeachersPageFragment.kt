package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentTeachersBinding
import techstack.course.androidproject.recyclerView.TeacherEntityItem
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
                    mAdapter.models = listOf()
                }

                TeachersViewModel.TeachersScreenState.LoadingState -> {
                    binding.progressIndicator.isVisible = true
                }

                is TeachersViewModel.TeachersScreenState.ResultState -> {
                    mAdapter.models = it.teachers
                }
            }
        }

        binding.addTeacher.setOnClickListener {
            showAlertDialogButtonClicked()
        }

        binding.removeTeacher.setOnClickListener {
            teachersViewModel.removeTeacher(TeacherEntityItem("Name", "Surname", 19))
        }
    }


    private fun showAlertDialogButtonClicked() {
        // Create an alert builder
        var alertDialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add teacher")

        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.teacher_data_dialog, null)

        val etName = customLayout.findViewById<EditText>(R.id.name)
        val etSurname = customLayout.findViewById<EditText>(R.id.surname)
        val etAge = customLayout.findViewById<EditText>(R.id.age)

        customLayout.findViewById<Button>(R.id.add).setOnClickListener {
            teachersViewModel.addTeacher(
                TeacherEntityItem(
                    etName.text.toString(),
                    etSurname.text.toString(),
                    etAge.text.toString().toInt(),
                )
            )
            alertDialog?.cancel()
        }

        builder.setView(customLayout)

        alertDialog = builder.create()
        alertDialog.show()
    }
}