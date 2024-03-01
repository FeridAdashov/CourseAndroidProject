package techstack.course.androidproject.recyclerView

import androidx.recyclerview.widget.RecyclerView
import techstack.course.androidproject.databinding.TeacherAdapterItemBinding


class TeacherDataHolder(
    private val binding: TeacherAdapterItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(entityItem: TeacherEntityItem) {
        binding.data = entityItem
    }
}