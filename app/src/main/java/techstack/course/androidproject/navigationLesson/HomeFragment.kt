package techstack.course.androidproject.navigationLesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.btnNotification.setOnClickListener {
            //Bu usul daha yaxsidir
            val action =
                HomeFragmentDirections.actionHomeFragmentToNotificationsFragment(myData = "AAAAAAAA")
            findNavController().navigate(action)

            //Pis usul)
//            findNavController().navigate(R.id.action_homeFragment_to_notificationsFragment)
        }
    }
}