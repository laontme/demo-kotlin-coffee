package me.laont.demo.kotlin.coffee

class CoffeeMachine {
    var water = 400
    var milk = 540
    var coffee = 120
    var cups = 9
    var money = 550

    var state = State.CHOOSING

    init {
        print("Write action (buy, fill, take, remaining, exit): ")
    }

    enum class State {
        CHOOSING, BUYING, ADD_WATER, ADD_MILK, ADD_COFFEE, ADD_CUPS
    }

    enum class Drink(val water: Int, val milk: Int, val coffee: Int, val price: Int) {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        fun brew(machine: CoffeeMachine) {
            when {
                machine.water < water -> {
                    println("Sorry, not enough water!")
                    return
                }
                machine.milk < milk -> {
                    println("Sorry, not enough milk!")
                    return
                }
                machine.coffee < coffee -> {
                    println("Sorry, not enough coffee!")
                    return
                }
                machine.cups < 1 -> {
                    println("Sorry, not enough cups!")
                    return
                }
            }

            println("I have enough resources, making you a coffee!")

            machine.water -= water
            machine.milk -= milk
            machine.coffee -= coffee
            machine.money += price
            machine.cups -= 1
        }
    }

    fun input(input: String): Boolean {
        when (state) {
            State.CHOOSING -> {
                println()

                when (input) {
                    "buy" -> {
                        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                        state = State.BUYING
                    }
                    "fill" -> {
                        print("Write how many ml of water do you want to add: ")
                        state = State.ADD_WATER
                    }
                    "take" -> {
                        println("I gave you \$$money")
                        println()
                        money = 0

                        idling()
                    }
                    "remaining" -> {
                        println("The coffee machine has:")
                        println("$water ml of water")
                        println("$milk ml of milk")
                        println("$coffee g of coffee beans")
                        println("$cups disposable cups")
                        println("\$$money of money")
                        println()

                        idling()
                    }
                    "exit" -> return false
                }
            }
            State.BUYING -> {
                when (input) {
                    "1" -> Drink.ESPRESSO.brew(this)
                    "2" -> Drink.LATTE.brew(this)
                    "3" -> Drink.CAPPUCCINO.brew(this)
                }
                println()
                idling()
            }
            State.ADD_WATER -> {
                water += input.toInt()
                print("Write how many ml of milk do you want to add: ")
                state = State.ADD_MILK
            }
            State.ADD_MILK -> {
                milk += input.toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                state = State.ADD_COFFEE
            }
            State.ADD_COFFEE -> {
                coffee += input.toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                state = State.ADD_CUPS
            }
            State.ADD_CUPS -> {
                println()
                cups += input.toInt()
                idling()
            }
        }

        return true
    }

    fun idling() {
        print("Write action (buy, fill, take, remaining, exit): ")
        state = State.CHOOSING
    }
}