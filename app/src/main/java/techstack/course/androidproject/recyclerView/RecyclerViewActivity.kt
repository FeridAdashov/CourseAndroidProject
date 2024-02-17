package techstack.course.androidproject.recyclerView

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import techstack.course.androidproject.databinding.ActivityRecyclerViewBinding
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference


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


        var data: String? = "This is some data"
        val softReference = WeakReference(data)

        // Access the value through the SoftReference

        // Access the value through the SoftReference
        var retrievedData = softReference.get()
        println("Retrieved data: $retrievedData")

        // Set data to null, making it eligible for garbage collection

        // Set data to null, making it eligible for garbage collection
        data = null

        // Force garbage collection (for illustration purposes)

        // Force garbage collection (for illustration purposes)
        System.gc()

        // Try to retrieve the data again

        // Try to retrieve the data again
        retrievedData = softReference.get()
        println("Retrieved data after GC: $retrievedData")


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