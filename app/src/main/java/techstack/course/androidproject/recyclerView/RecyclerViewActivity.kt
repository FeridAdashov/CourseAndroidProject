package techstack.course.androidproject.recyclerView

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import techstack.course.androidproject.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private val mAdapter: BookingsAdapter by lazy {
        BookingsAdapter(object : BookingsAdapter.BookingsListener {
            override fun join(item: BookingEntityItem) {
                Toast.makeText(this@RecyclerViewActivity, "HAHAHAHAHA JOIN", Toast.LENGTH_LONG)
                    .show()
            }

            override fun decline(item: BookingEntityItem) {
                Toast.makeText(this@RecyclerViewActivity, "HAHAHAHAHA DECLINE", Toast.LENGTH_LONG)
                    .show()
            }

            override fun changeTitle(text: String) {

            }
        }).apply {
            setHasStableIds(true)
        }
    }


    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bookingsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(
                    this@RecyclerViewActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = mAdapter
        }

        mAdapter.models = List(50) {
            BookingEntityItem(
                0,
                if (it % 2 == 0) "Title CUT" else "Title TEK",
                "Description 1",
                "UpcomingText 1",
                "09:00",
                "12.10.2024",
                "2 hours",
                user = BookingEntityItem.UserEntity(
                    0,
                    "Farid Adashov",
                    "https://static.vecteezy.com/system/resources/previews/026/382/284/non_2x/hip-hop-singer-or-rapper-singing-illustration-hip-hop-rap-artist-wearubg-a-hat-and-holding-a-mic-black-and-white-image-vector.jpg"
                )
            )
        }
    }
}