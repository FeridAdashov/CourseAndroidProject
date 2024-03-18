package techstack.course.androidproject.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import techstack.course.androidproject.databinding.TeacherAdapterItemBinding


class TeachersAdapter(private val teachersAdapterListener: TeachersAdapterListener) :
    RecyclerView.Adapter<TeacherDataHolder>() {

    interface TeachersAdapterListener {
        fun onItemLongClick(item: TeacherEntityItem)
    }

    var models: List<TeacherEntityItem> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeacherDataHolder {
        val binding =
            TeacherAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TeacherDataHolder(binding)
    }

    override fun onBindViewHolder(resultHolder: TeacherDataHolder, position: Int) {
        resultHolder.bind(models[resultHolder.adapterPosition], teachersAdapterListener)
    }

    override fun getItemCount() = models.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}