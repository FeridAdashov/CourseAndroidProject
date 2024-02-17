package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentNotificationsBinding

class A
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private lateinit var binding: FragmentNotificationsBinding

    //by keyword delegation
    val args: NotificationsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNotificationsBinding.bind(view)

        Log.d("SSSSSSS", args.myData ?: "0")


        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
