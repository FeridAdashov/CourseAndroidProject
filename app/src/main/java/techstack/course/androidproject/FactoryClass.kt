package techstack.course.androidproject


interface FactoryClassCar

interface Ferrari : FactoryClassCar {
    fun one()
    fun one2()
    fun one3()
}

class Toyota : FactoryClassCar
class Kia(val title: String = "MyKiaaaa") : FactoryClassCar


enum class CarType {
    FERRARI,
    Toyota,
    Kia,
}

fun carFactoryFunction(carType: CarType): FactoryClassCar {
    return when (carType) {
        CarType.FERRARI -> object : Ferrari {
            override fun one() {
                println("ONEEEEE")
            }

            override fun one2() {

            }

            override fun one3() {
            }
        }

        CarType.Toyota -> Toyota()
        CarType.Kia -> Kia()
    }
}


fun main() {
    val myCar = carFactoryFunction(CarType.FERRARI) as Ferrari

    println(myCar.one())

}