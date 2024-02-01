package techstack.course.androidproject.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import techstack.course.androidproject.R
import techstack.course.androidproject.databinding.BookingAdapterItemBinding


class BookingDataHolder(
    private val binding: BookingAdapterItemBinding,
    private val listener: BookingsAdapter.BookingsListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(entityItem: BookingEntityItem) {
        binding.run {
            tvTitle.text = entityItem.title
            tvTime.text = entityItem.time

            btnJoin.setOnClickListener { listener.join(entityItem) }
            btnDecline.setOnClickListener { listener.decline(entityItem) }

            if (!entityItem.user.photo.isNullOrEmpty())
                Glide
                    .with(itemView.context)
                    .load(entityItem.user.photo)
                    .centerCrop()
                    .placeholder(R.drawable.img_poster)
                    .into(imgAvatar)
        }
    }
}