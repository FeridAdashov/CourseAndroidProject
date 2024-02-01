package techstack.course.androidproject.recyclerView

import java.io.Serializable

data class BookingEntityItem(
    val id: Int,
    val title: String,
    val description: String,
    val upcomingText: String,
    val time: String,
    val date: String,
    val duration: String,
    val user: UserEntity,
) : Serializable{

    data class UserEntity(
        val id: Int,
        val name: String,
        val photo: String? = null,
    ) : Serializable
}
