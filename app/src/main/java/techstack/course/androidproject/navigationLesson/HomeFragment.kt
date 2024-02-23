package techstack.course.androidproject.navigationLesson

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.FragmentHomeBinding
import techstack.course.androidproject.viewModel.HomeUIDataListener
import techstack.course.androidproject.viewModel.HomeViewModel
import techstack.course.androidproject.viewModel.HomeViewModelFactory

//viewModel how survive data

@SuppressLint("SetTextI18n")
class HomeFragment : Fragment(R.layout.fragment_home), HomeUIDataListener {
    private lateinit var binding: FragmentHomeBinding


    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(this)
    }

//    private val homeViewModel = HomeViewModel(this)


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

        binding.btnSum.setOnClickListener {
            sumClick()
        }

        binding.btnMultiply.setOnClickListener {
            multipleClick()
        }

        homeViewModel.data?.let {
            setUiData(it)
        }
    }

    private fun sumClick() {
        homeViewModel.sum(1, 2, 3, 4, 5)
    }

    private fun multipleClick() {
        homeViewModel.multiply(1, 2, 3, 4, 5)
    }

    /**
     * Put UI data with this function
     */

    private fun setUiData(data: Int) {
        binding.tvData.text = "Sum: $data"
    }

    override fun onResult(data: Int) {
        setUiData(data)
    }
}