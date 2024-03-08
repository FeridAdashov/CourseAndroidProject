package techstack.course.androidproject.recyclerView

import kotlin.random.Random

data class TeacherEntityItem(
    val name: String,
    val surname: String,
    val age: Int,
    private var id: Int? = null,
) {
    init {
        id = Random.nextInt()
    }
}