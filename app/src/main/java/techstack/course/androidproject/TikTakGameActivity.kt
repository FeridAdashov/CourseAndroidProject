package techstack.course.androidproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import techstack.course.androidproject.databinding.ActivityTikTakGameBinding

//, object : CardGameAttemptsListener {
//    override fun attempt() {
//        whenAttempt()
//    }
//
//    override fun finish(status: Status) {
//        whenFinish(status)
//    }
//}

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityTikTakGameBinding

    private val gameDimension = 4

    private val mCardsAdapter: TikTakGameGridAdapter by lazy {
        TikTakGameGridAdapter(this)
    }

    private fun whenAttempt() {

    }

    private fun whenFinish(status: Status) {
        Toast.makeText(this, status.title, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTikTakGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val displayMetrics = resources.displayMetrics
        val size = displayMetrics.widthPixels / gameDimension

        binding.gridview.apply {
            adapter = mCardsAdapter
            numColumns = gameDimension
        }

        val list = arrayListOf<ArrayList<Status>>()
        repeat(gameDimension) {
            val l = arrayListOf<Status>()
            repeat(gameDimension) {
                l.add(Status.EMPTY)
            }
            list.add(l)
        }

        mCardsAdapter.cards = list
        mCardsAdapter.cardDimension = size - 10
    }
}
