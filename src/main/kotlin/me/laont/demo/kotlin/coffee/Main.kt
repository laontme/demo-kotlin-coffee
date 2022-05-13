package me.laont.demo.kotlin.coffee

fun main() {
    val machine = CoffeeMachine()

    while (true) {
        if (!machine.input(readln())) break
    }
}