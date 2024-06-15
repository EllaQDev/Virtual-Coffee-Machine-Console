// First, read the numbers of coffee drinks from the input.
// Figure out how much of each ingredient the machine will need.
// Note that one cup of coffee made on this coffee machine contains
// 200 ml of water, 50 ml of milk, and 15 g of coffee beans.
// Output the required ingredient amounts back to the user.
fun main() {
    // prompt user for # cups
    println("Write how many cups of coffee you will need:")
    //read input
    val numCups = readln().toInt()
    //generate response of ingredients needed
    println("For $numCups cups of coffee you will need:")
    val listInd = listOf("water" to "200 ml", "milk" to "50 ml", "coffee beans" to "15 g")
    listInd.forEach {
        val (factor, label) = it.second.split(" ")
        println("${factor.toInt() * numCups} $label of ${it.first}")
    }
}