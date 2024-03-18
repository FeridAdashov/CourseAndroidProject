package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentAddBinding
import techstack.course.androidproject.viewModel.FlowsViewModel

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding

    private val flowsViewModel: FlowsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)

        binding.btnLiveData.setOnClickListener {
            flowsViewModel.triggerLiveData()
        }

        binding.btnFlow.setOnClickListener {}

        binding.btnStateFlow.setOnClickListener {
            flowsViewModel.triggerStateFlow()
        }

        binding.btnSharedFlow.setOnClickListener {
            flowsViewModel.triggerSharedFlow()
        }

        binding.btnResult.setOnClickListener {
        }

        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        flowsViewModel.livedata.observe(viewLifecycleOwner) {
            binding.tvLiveData.text = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flowsViewModel.mFlow.collectLatest {
                    binding.tvFlow.text = it
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flowsViewModel.stateFlow.collectLatest {
                    binding.tvStateFlow.text = it
                    showAlertDialogForAdd()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flowsViewModel.sharedFlow.collectLatest {
                    binding.tvSharedFlow.text = it

                    showAlertDialogForAdd()
                }
            }
        }
    }

    private fun showAlertDialogForAdd() {
        // Create an alert builder
        var alertDialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add teacher")

        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.teacher_data_dialog, null)

        customLayout.findViewById<Button>(R.id.add).setOnClickListener {
            alertDialog?.cancel()
        }

        builder.setView(customLayout)

        alertDialog = builder.create()
        alertDialog.show()
    }
}