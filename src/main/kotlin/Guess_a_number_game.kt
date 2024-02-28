package org.example

fun main(){
    val game = Game()
    while (true) {
        game.execute()

        if (game.askToEnd()) return
    }
}

class Game {
    private var _number: Int? = null

    private fun generateRandomNumber() {
        _number = (0..100).random()
        println("The magic number is: $_number")
    }

    fun askToEnd() : Boolean{
        val userAction: String = readln()

        return userAction != "y"
    }

    fun execute(){
        if (_number == null) generateRandomNumber()

        do {
            println("-----------------------------")
            println("Enter a number from 0 to 100:")
            val inputNumber = readln().trim().toIntOrNull()

            if (inputNumber == null) {
                println("You didn't enter the number\nPlease try again!")
            } else {
                if (inputNumber > _number!!) {
                    println("Your number is more")
                }

                if (inputNumber < _number!!) {
                    println("Your number is less")
                }
                if (inputNumber == _number){
                    println("Congratulations! You've won!\nDo you want to try again?")
                    println("Enter y, to play again, n - to exit from game")
                    break;
                }
            }

        } while (inputNumber != _number)

        _number = null
    }
}