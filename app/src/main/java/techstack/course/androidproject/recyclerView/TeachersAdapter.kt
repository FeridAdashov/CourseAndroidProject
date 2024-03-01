package techstack.course.androidproject.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import techstack.course.androidproject.databinding.TeacherAdapterItemBinding


class TeachersAdapter : RecyclerView.Adapter<TeacherDataHolder>() {

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
        resultHolder.bind(models[resultHolder.adapterPosition])
    }

    override fun getItemCount() = models.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}