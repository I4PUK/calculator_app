package org.example

fun main(){
    var testCase1 : String = "5 * ( 5 - 2)";
    val interpreter = MathExpressionIntepreter("5*(5-2)")
    val intepreter1 = MathExpressionIntepreter(testCase1)
    val result = interpreter.evaluate()

     print(result)
}

enum class Operation(val operation: String) {
    multiplication("*"),
    divide("/"),
    add("+"),
    subtraction("-");
}


class MathExpressionIntepreter(expression: String) {
    private val _expression = expression;

    fun evaluate(): Float {
        var processedExpression = calculateBrackets(_expression);

        processedExpression = evaluateOperation(processedExpression, Operation.multiplication.operation);
        processedExpression = evaluateOperation(processedExpression, Operation.divide.operation);
        processedExpression = evaluateOperation(processedExpression, Operation.add.operation);
        processedExpression = evaluateOperation(processedExpression, Operation.subtraction.operation);

        return if (processedExpression.toFloatOrNull() != null) processedExpression.toFloat() else 0.0F;
    }

    private fun calculateBrackets(expression: String): String {
        var localExpression: String = expression;

        while (localExpression.contains(')')) {
            val closingBracketIndex: Int = localExpression.indexOf(')')
            val localSubstring = localExpression.substring(0, closingBracketIndex)
            val openingBracketIndex: Int = localSubstring.lastIndexOf('(')
            val localSub1 = localSubstring.substring(openingBracketIndex + 1, closingBracketIndex)

            val bracketContent: String =
                expression.substring(openingBracketIndex + 1, closingBracketIndex)
            val bracketResult: Float = evaluateExpression(bracketContent).toFloat()
            localExpression = expression.replaceRange(
                openingBracketIndex,
                closingBracketIndex + 1, bracketResult.toString()
            )
        }

        return localExpression
    }

    private fun evaluateExpression(expression: String): Float {
        val regex = """\d+(\.\d+)?""".toRegex()
        val listOfNumbers = regex.findAll(expression).map { it.value }.toList()
        val splittedOperators: String =
            Regex("[^+\\-*/]").replace(expression, " ").filter { it.toString().trim().isNotEmpty() }

        var result: Float = listOfNumbers[0].toFloat()

        for (i in splittedOperators.indices) {
            when (splittedOperators[i]) {
                '+' -> result += listOfNumbers[i + 1].toFloat();
                '-' -> result -= listOfNumbers[i + 1].toFloat();
                '*' -> result *= listOfNumbers[i + 1].toFloat();
                '/' -> result /= listOfNumbers[i + 1].toFloat();
            }
        }

        return result;
    }

    private fun evaluateOperation(
        expression: String,
        operation: String,
    ): String {
        var localExpression = expression
        val regex = """\d+(\.\d+)?""".toRegex()
        val numericalValues = regex.findAll(localExpression).map { it.value }.toList()

        val operators: String =
            Regex("[^+\\-*/]").replace(localExpression, " ").filter { it.toString().trim().isNotEmpty() }


        while (localExpression.contains(operation)) {
            val operationIndex: Int = operators.lastIndexOf(operation)

            val leftOperand: String = numericalValues[operationIndex]
            val rightOperand: String = numericalValues[operationIndex + 1]

            val bracketContent: String = "$leftOperand$operation$rightOperand"

            val bracketResult: Float = evaluateExpression(bracketContent)
            localExpression = localExpression.replace(bracketContent, "$bracketResult")
        }

        return localExpression
    }
}