package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentNotificationsBinding
import techstack.course.androidproject.viewModel.NotificationViewModel


class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private lateinit var binding: FragmentNotificationsBinding

    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNotificationsBinding.bind(view)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

//        Thread {
//            while (true) {
//                Thread.sleep(10)
//                activity?.runOnUiThread {
//                    binding.data.text = notificationViewModel.timerLiveData.value.toString()
//                }
//            }
//        }.start()

        notificationViewModel.timerLiveData.observe(viewLifecycleOwner) {
            binding.data2.text = "$it"
            binding.data3.text = "$it"
        }
    }
}
