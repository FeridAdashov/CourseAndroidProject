package techstack.course.androidproject.activity

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import kotlinx.parcelize.Parcelize
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.ActivityLessonBinding


@Parcelize
data class MyData(val name: String, val surname: String) : Parcelable

class LessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)

        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> changeStartDestination(R.id.homeFragment)
                R.id.add -> changeStartDestination(R.id.addFragment)
                R.id.settings -> changeStartDestination(R.id.settingsFragment)
            }
            true
        }
    }

    private fun changeStartDestination(destinationId: Int) =
        findNavController(R.id.fragmentContainerView).apply {
            graph = navInflater.inflate(R.navigation.nav_graph).apply {
                setStartDestination(destinationId)
            }
        }
}




