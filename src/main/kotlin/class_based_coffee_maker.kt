class CoffeeMaker(var water: Int, var milk: Int, var beans: Int, var cups: Int, var money: Int) {
    fun determineMaxCups(drink: Drink): Pair<Int, List<Int>> {
        val maxByWater = water/drink.water.factor
        val milkNeeded = drink.milk?.factor ?: 0
        //may need to find a more satisfactory solution here, setting max when milk is not in recipe to 9999
        //to make it unlikely to limit the min number output when making just espressos
        var maxByMilk : Int = 9999
        if (milkNeeded != 0) {
             maxByMilk = milk/milkNeeded
        }
        val maxByBeans = beans/drink.beans.factor
        val maxByCups = cups/1

        return listOf(maxByWater, maxByMilk, maxByBeans, maxByCups).min() to listOf(maxByWater, maxByMilk, maxByBeans, maxByCups)
    }
    fun displayMachineLevels() {
        println("""
            The coffee machine has:
            $water ml of water
            $milk ml of milk
            $beans g of coffee beans
            $cups disposable cups
            $$money of money
        """.trimIndent())
    }
    fun buyDrink() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val drinkInput = readln()
        if (drinkInput == "back") {
        } else {
            val drinkNum = drinkInput.toInt()
            val drink : Drink = when (drinkNum) {
                1 -> Espresso()
                2 -> Latte()
                else -> Cappuccino()
            }
            val (max, maxList) = determineMaxCups(drink)
            if (max > 0){
                println("I have enough resources, making you a coffee!")
                makeDrink(drink)
            } else {
                val indexOfMissingIng = maxList.indexOf(0)
                val missingResource = when (indexOfMissingIng) {
                    0 -> "water"
                    1 -> "milk"
                    2 -> "coffee beans"
                    else -> "cups"
                }
                println("sorry not enough $missingResource!")
            }
        }
    }
    fun makeDrink(drink: Drink) {
        money += drink.cost
        beans -= drink.beans.factor
        milk -= drink.milk?.factor ?: 0
        water -= drink.water.factor
        cups--
        //displayMachineLevels()
    }
    fun restockMachine() {
        println("Write how many ml of water you want to add: ")
        this.water += readln().toInt()
        println("Write how many ml of milk you want to add: ")
        this.milk += readln().toInt()
        println("Write how many grams of coffee beans you want to add: ")
        this.beans += readln().toInt()
        println("Write how many disposable cups you want to add: ")
        this.cups += readln().toInt()
        //displayMachineLevels()
    }
    fun withdrawFunds() {
        println("I gave you $$money")
        money = 0
        //displayMachineLevels()
    }
}
class Coffee(override val water: Ingredient = Ingredient("water",200, "ml"),
    override val milk: Ingredient = Ingredient("milk",50, "ml"),
    override val beans: Ingredient = Ingredient("coffee beans", 15, "g" ),
    override val cost: Int = 3): Drink(water, milk, beans, cost)

class Espresso(override val water: Ingredient = Ingredient("water", 250, "ml"),
    override val milk: Ingredient? = null,
    override val beans: Ingredient = Ingredient("coffee beans", 16, "g"),
    override val cost: Int = 4): Drink(water, milk, beans, cost)

class Latte(override val water: Ingredient = Ingredient("water",350, "ml"),
    override val milk: Ingredient = Ingredient("milk",75, "ml"),
    override val beans: Ingredient = Ingredient("coffee beans", 20, "g" ),
    override val cost: Int = 7): Drink(water, milk, beans, cost)
class Cappuccino(override val water: Ingredient = Ingredient("water",200, "ml"),
    override val milk: Ingredient = Ingredient("milk",100, "ml"),
    override val beans: Ingredient = Ingredient("coffee beans", 12, "g" ),
    override val cost: Int = 6): Drink(water, milk, beans, cost)
open class Drink(open val water: Ingredient, open val milk: Ingredient?, open val beans: Ingredient, open val cost: Int)
class Ingredient(val name: String, val factor: Int, val label: String)

fun main() {
//    println("Write how many ml of water the coffee machine has:")
//    val water = readln().toInt()
//    println("Write how many ml of milk the coffee machine has:")
//    val milk = readln().toInt()
//    println("Write how many grams of coffee beans the coffee machine has:")
//    val beans = readln().toInt()
//    println("Write how many cups of coffee you will need:")
//    val cupsNeeded = readln().toInt()
    val init_water = 400
    val init_milk = 540
    val init_beans = 120
    val init_cups = 9
    val init_money = 550
    val coffeeMaker = CoffeeMaker(init_water, init_milk, init_beans, init_cups, init_money)
//    val max = coffeMaker.determineMaxCups(Coffee())
//    val diffOrderMax = max - cupsNeeded
//    println(when {
//        diffOrderMax == 0 -> "Yes, I can make that amount of coffee"
//        diffOrderMax > 0 -> "Yes, I can make that amount of coffee (and even $diffOrderMax more than that)"
//        else -> "No, I can make only $max cups of coffee"
//    })
    //coffeeMaker.displayMachineLevels()
    //prompt user action
    while(true) {
        println("Write action (buy, fill, take, remaining, exit): ")
        val action = readln()
        when (action) {
            "buy" -> coffeeMaker.buyDrink()
            "fill" -> coffeeMaker.restockMachine()
            "take" -> coffeeMaker.withdrawFunds()
            "remaining" -> coffeeMaker.displayMachineLevels()
            "exit" -> break
        }
    }
}