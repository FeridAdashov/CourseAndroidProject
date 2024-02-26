package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)

        binding.btnGoToTeachersPage.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToTeachersFragment())
        }
    }
}