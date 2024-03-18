package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentTeachersBinding
import techstack.course.androidproject.recyclerView.TeacherEntityItem
import techstack.course.androidproject.recyclerView.TeachersAdapter
import techstack.course.androidproject.viewModel.TeachersViewModel


class TeachersPageFragment : Fragment(R.layout.fragment_teachers) {
    private lateinit var binding: FragmentTeachersBinding

    private val teachersAdapterListener = object : TeachersAdapter.TeachersAdapterListener {
        override fun onItemLongClick(item: TeacherEntityItem) {
            teachersViewModel.removeTeacher(item)
        }
    }

    private val mAdapter: TeachersAdapter by lazy {
        TeachersAdapter(teachersAdapterListener).apply {
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

//        teachersViewModel.teachersScreenStateLiveData.observe(viewLifecycleOwner) {
//            binding.progressIndicator.isGone = true
//            binding.tvError.isGone = true
//
//            when (it) {
//                is TeachersViewModel.TeachersScreenState.ErrorState -> {
//                    binding.tvError.isVisible = true
//                    binding.tvError.text = it.message
//                    mAdapter.models = listOf()
//                }
//
//                TeachersViewModel.TeachersScreenState.LoadingState -> {
//                    binding.progressIndicator.isVisible = true
//                }
//
//                is TeachersViewModel.TeachersScreenState.ResultState -> {
//                    mAdapter.models = it.teachers
//                }
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                teachersViewModel.teachersScreenStateFlow.collectLatest {
                    uiInitialState()

                    when (it) {
                        TeachersViewModel.TeachersScreenState.InitialState -> uiInitialState()

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
            }
        }

        binding.addTeacher.setOnClickListener {
            showAlertDialogForAdd()
        }

        binding.removeTeacher.setOnClickListener {
            showAlertDialogForDelete()
        }
    }

    private fun uiInitialState() {
        binding.progressIndicator.isGone = true
        binding.tvError.isGone = true
    }

    private fun showAlertDialogForAdd() {
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

    private fun showAlertDialogForDelete() {
        // Create an alert builder
        var alertDialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add teacher")

        // set the custom layout
        val customLayout: View =
            layoutInflater.inflate(R.layout.teacher_data_dialog_for_delete, null)

        val etId = customLayout.findViewById<EditText>(R.id.etId)

        customLayout.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val id = etId.text.toString().toIntOrNull()
            if (id == null) {
                Toast.makeText(requireContext(), "Dogru id daxil etmediniz!!!", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            teachersViewModel.removeTeacher(id)
            alertDialog?.cancel()
        }

        builder.setView(customLayout)

        alertDialog = builder.create()
        alertDialog.show()
    }
}