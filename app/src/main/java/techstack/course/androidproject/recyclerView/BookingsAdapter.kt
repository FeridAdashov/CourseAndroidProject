package techstack.course.androidproject.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import techstack.course.androidproject.databinding.BookingAdapterItemBinding


class BookingsAdapter(private val onBookingsListener: BookingsListener) :
    RecyclerView.Adapter<BookingDataHolder>() {

    interface BookingsListener {
        fun join(item: BookingEntityItem)
        fun decline(item: BookingEntityItem)
        fun changeTitle(text: String)
    }

    var models: List<BookingEntityItem> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingDataHolder {
        val binding =
            BookingAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BookingDataHolder(binding, onBookingsListener)
    }

    override fun onBindViewHolder(resultHolder: BookingDataHolder, position: Int) {
        resultHolder.bind(models[resultHolder.adapterPosition])
    }

    override fun getItemCount() = models.size
}