package techstack.course.androidproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import techstack.course.androidproject.databinding.ActivityDataBinding



class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvData.setOnClickListener {
        }

    }
}