package techstack.course.androidproject


class FoodOrder private constructor(
    val bread: String?,
    val condiments: String?,
    val objOne: String?
) {

    class Builder {
        var bread: String = ""
        var condiments: String? = null
        var objOne: String? = null

        //bu funksiya ile apply olanin strukturu eynidir
        fun bread(bread: String): Builder {
            this.bread = bread
            return this
        }

        fun condiments(condiments: String) = apply { this.condiments = condiments }

        fun objOne(objOne: String) = apply { this.objOne = objOne }

        fun build() = FoodOrder(bread, condiments, objOne)
    }
}


fun main() {
    val foodOrder = FoodOrder.Builder()
        .bread("Corek")
        .condiments("My djksfh")
        .objOne("Obfdsfsgf")
        .bread("AHAHAHAH")
        .build()

    println(foodOrder.bread ?: "")
}