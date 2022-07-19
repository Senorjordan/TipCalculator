package com.angrycock.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.angrycock.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { calculateTip() }  //MAIN CODE
    }



    //MAIN fun -> done everytime the button is pushed
    private fun calculateTip() {
        val stringInTextField = binding.editText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipAmount.text = ""
            return
        }

        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioBtn1 -> 0.20
            R.id.radioBtn2 -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if (binding.switch3.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}
