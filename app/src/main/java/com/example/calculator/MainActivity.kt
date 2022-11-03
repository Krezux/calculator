package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // text labels
    val bottom_text: TextView = findViewById(R.id.textView)
    val top_text: TextView = findViewById(R.id.textView2)

        // extra variables
        var canAddOperation = false
        var canAddDecimal = true

        // main buttons
        val ac_button : Button = findViewById(R.id.button19)
        val equal_button : Button = findViewById(R.id.button43)
        val backspace_button : Button = findViewById(R.id.button29)

        // operators
        val percentage_button : Button = findViewById(R.id.button30)
        val divide_button : Button = findViewById(R.id.button31)
        val multiply_button : Button = findViewById(R.id.button34)
        val minus_button : Button = findViewById(R.id.button37)
        val plus_button : Button = findViewById(R.id.button40)

        //numbers

        val one_button : Button = findViewById(R.id.button23)
        val two_button : Button = findViewById(R.id.button38)
        val three_button : Button = findViewById(R.id.button39)
        val four_button : Button = findViewById(R.id.button17)
        val five_button : Button = findViewById(R.id.button35)
        val six_button : Button = findViewById(R.id.button36)
        val seven_button : Button = findViewById(R.id.button18)
        val eight_button : Button = findViewById(R.id.button32)
        val nine_button : Button = findViewById(R.id.button33)
        val zero_button : Button = findViewById(R.id.button41)
        val decimal_button : Button = findViewById(R.id.button22)
        // functions
        fun numberAction(text: String)
        {
            if(text == ".")
            {
                if(canAddDecimal)
                {
                    top_text.append(text)
                }
                canAddDecimal = false
            }
            else
            {
                top_text.append(text)
            }

            canAddOperation = true
        }

        fun operatorAction(text: String)
        {
            if(canAddOperation)
            {
                top_text.append(text)
                canAddOperation = false
                canAddDecimal = true
            }

        }

        fun allClearAction()
        {
            top_text.text = ""
            bottom_text.text = ""
        }

        fun backSpaceAction()
        {
            val length = top_text.length()

            if(length > 0)
            {
                top_text.text = top_text.text.subSequence(0, length - 1)
            }

        }


        fun digitsOperators(): MutableList<Any>
        {
            val list = mutableListOf<Any>()
            var currentDigit = ""

            for(character in top_text.text)
            {
                if(character.isDigit() || character == '.')
                {
                    currentDigit += character
                }
                else
                {
                    list.add(currentDigit.toFloat())
                    currentDigit = ""
                    list.add(character)
                }
            }

            if(currentDigit != "")
            {
                list.add(currentDigit.toFloat())
            }

            return list
        }

        fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any>
        {
            val newList = mutableListOf<Any>()
            var restartIndex = passedList.size

            for(i in passedList.indices)
            {
                if(passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
                {
                    val operator = passedList[i]
                    val prevDigit = passedList[i - 1] as Float
                    val nextDigit = passedList[i + 1] as Float
                    when(operator)
                    {
                        'x' ->
                        {
                            newList.add(prevDigit * nextDigit)
                            restartIndex = i + 1
                        }
                        '/' ->
                        {
                            newList.add(prevDigit / nextDigit)
                            restartIndex = i + 1
                        }
                        else ->
                        {
                            newList.add(prevDigit)
                            newList.add(operator)
                        }
                    }
                }

                if(i > restartIndex)
                    newList.add(passedList[i])
            }

            return newList
        }

        fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any>
        {
            var list = passedList

            while (list.contains('x') || list.contains('/'))
            {
                list = calcTimesDiv(list)
            }
            return list
        }

        fun addSubtractCalculate(passedList: MutableList<Any>): Float
        {
            var result = passedList[0] as Float

            for(i in passedList.indices)
            {
                if(passedList[i] is Char && i != passedList.lastIndex)
                {
                    val operator = passedList[i]
                    val nextDigit = passedList[i + 1] as Float

                    if (operator == '+')
                    {
                        result += nextDigit
                    }
                    if (operator == '-')
                    {
                        result -= nextDigit
                    }

                }
            }

            return result
        }

        fun calculateResults(): String
        {
            val digitsOperators = digitsOperators()

            if(digitsOperators.isEmpty())
            {
                return ""
            }

            val timesDivision = timesDivisionCalculate(digitsOperators)

            if(timesDivision.isEmpty())
            {
                return ""
            }

            val result = addSubtractCalculate(timesDivision)
            return result.toString()
        }

        fun equalsAction()
        {
            bottom_text.text = calculateResults()
        }

        // button clicks

        one_button.setOnClickListener()
        {
            numberAction(one_button.text as String)
        }

        two_button.setOnClickListener()
        {
            numberAction(two_button.text as String)
        }

        three_button.setOnClickListener()
        {
            numberAction(three_button.text as String)
        }

        four_button.setOnClickListener()
        {
            numberAction(four_button.text as String)
        }

        five_button.setOnClickListener()
        {
            numberAction(five_button.text as String)
        }

        six_button.setOnClickListener()
        {
            numberAction(six_button.text as String)
        }

        seven_button.setOnClickListener()
        {
            numberAction(seven_button.text as String)
        }

        eight_button.setOnClickListener()
        {
            numberAction(eight_button.text as String)
        }

        nine_button.setOnClickListener()
        {
            numberAction(nine_button.text as String)
        }

        zero_button.setOnClickListener()
        {
            numberAction(zero_button.text as String)
        }

        //
        backspace_button.setOnClickListener()
        {
            backSpaceAction()
        }

        //
        percentage_button.setOnClickListener()
        {
            operatorAction(percentage_button.text as String)
        }

        divide_button.setOnClickListener()
        {
            operatorAction(divide_button.text as String)
        }

        multiply_button.setOnClickListener()
        {
            operatorAction(multiply_button.text as String)
        }

        minus_button.setOnClickListener()
        {
            operatorAction(minus_button.text as String)
        }

        plus_button.setOnClickListener()
        {
            operatorAction(plus_button.text as String)
        }

        decimal_button.setOnClickListener()
        {
            numberAction(decimal_button.text as String)
        }

        equal_button.setOnClickListener()
        {
            equalsAction()
        }

        ac_button.setOnClickListener()
        {
            allClearAction()
        }
    }
}



