class CoffeeMaker(val water: Int, val milk: Int, val beans: Int) {
    fun determineMaxCups(drink: Drink): Int {
        val maxByWater = water/drink.water.factor
        val maxByMilk = milk/drink.milk.factor
        val maxByBeans = beans/drink.beans.factor
        return listOf(maxByWater, maxByMilk, maxByBeans).min()
    }
}
class Coffee(override val water: Ingredient = Ingredient("water",200, "ml"),
             override val milk: Ingredient = Ingredient("milk",50, "ml"),
             override val beans: Ingredient = Ingredient("coffee beans", 15, "g" )):
    Drink(water, milk, beans) {

}

open class Drink(open val water: Ingredient, open val milk: Ingredient, open val beans: Ingredient)
class Ingredient(val name: String, val factor: Int, val label: String)

fun main() {
    println("Write how many ml of water the coffee machine has:")
    val water = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val milk = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val beans = readln().toInt()
    println("Write how many cups of coffee you will need:")
    val cupsNeeded = readln().toInt()

    val coffeMaker = CoffeeMaker(water, milk, beans)
    val max = coffeMaker.determineMaxCups(Coffee())
    val diffOrderMax = max - cupsNeeded
    println(when {
        diffOrderMax == 0 -> "Yes, I can make that amount of coffee"
        diffOrderMax > 0 -> "Yes, I can make that amount of coffee (and even $diffOrderMax more than that)"
        else -> "No, I can make only $max cups of coffee"
    })
}