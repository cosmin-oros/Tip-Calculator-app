package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //call the calculateTip() function when you click the calculate button
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    fun calculateTip(){
        //get the string of the cost then convert it to double
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        //get the percentage option
        val selectedId = binding.tipOptions.checkedRadioButtonId

        //switch for choosing the tip percentage
        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_ten_percent -> 0.10
            R.id.option_five_percent -> 0.05
            else -> 0.0
        }

        //final tip value
        var tip = tipPercentage * cost

        //if it's checked round up the value of the tip
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }

        //format the number
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        //display the tip
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}