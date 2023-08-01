package com.androidperu.calculator.ui.calculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context

class CalculatorViewModel : ViewModel() {

    private val _inputText = mutableStateOf("")
    val inputText: State<String> = _inputText

    private val _outputText = mutableStateOf("")
    val outputText: State<String> = _outputText

    fun onEvent(e: CalculatorEvent) {
        when (e) {
            CalculatorEvent.AllClear -> allClear()
            CalculatorEvent.BackSpace -> backSpace()
            CalculatorEvent.Calculate -> calculate()
            is CalculatorEvent.Write -> write(e.value)
        }
    }

    private fun allClear() {
        _inputText.value = ""
        _outputText.value = ""
    }

    private fun backSpace() {
        if (_inputText.value.isNotEmpty()) {
            _inputText.value = _inputText.value.dropLast(1)
        }
    }

    private fun calculate() {
        val result = evaluateExpression(_inputText.value)
        _outputText.value = result
        _inputText.value = result
    }

    private fun write(value: String) {
        _inputText.value += value
    }

    private fun evaluateExpression(expression: String): String {
        val rhino = Context.enter()
        rhino.optimizationLevel = -1

        val scope = rhino.initStandardObjects()
        val cleanedExpression = expression.replace("×", "*").replace(Regex("(\\d)\\("), "$1*(")

        val modifiedExpression = addMultiplicationForConsecutiveParentheses(cleanedExpression)

        val result = rhino.evaluateString(scope, modifiedExpression, "javascript", 1, null)
        val roundedResult = result.toString().toDoubleOrNull()?.let { number ->
            if (number % 1 == 0.0) {
                number.toInt().toString()
            } else {
                number.toString()
            }
        } ?: result.toString()

        return roundedResult
    }

    private fun addMultiplicationForConsecutiveParentheses(expression: String): String {
        val builder = StringBuilder()

        for (i in expression.indices) {
            val currentChar = expression[i]
            builder.append(currentChar)

            if (currentChar == ')' && i < expression.length - 1 && expression[i + 1] == '(') {
                builder.append('*')
            }
        }

        return builder.toString()
    }

}
