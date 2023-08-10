package com.neslihanyagmurca.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.neslihanyagmurca.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity :AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.imageBoy.setOnClickListener {
            binding.imageBoy.setImageResource(R.drawable.boy)
            binding.imageGirl.setImageResource(R.drawable.girl)
        }
        binding.imageGirl.setOnClickListener {
            binding.imageBoy.setImageResource(R.drawable.boy)
            binding.imageGirl.setImageResource(R.drawable.girl)
        }
        binding.calculateButton.setOnClickListener {
                var heightValue=0.0
                var weightValue=0.0
            if (binding.weightValue.text.toString().isNotEmpty()){
                weightValue=binding.weightValue.text.toString().toDouble()
            }
            if (binding.heightValue.text.toString().isNotEmpty()){
                heightValue=(binding.heightValue.text.toString().toDouble()/100)
            }
            if (weightValue>0.0&&heightValue>0.0){
                val bmiValue=String.format("%.2f",weightValue/heightValue.pow(2))
                binding.bmi.text=bmiValue
                binding.bmiStatus.text=bmiStatusValue(weightValue/heightValue.pow(2))
                binding.bmiView.visibility= View.VISIBLE
                binding.calculateButton.visibility=View.GONE
            }
            else{
                Toast.makeText(this,"Please input Weight and Height values greater than 0.0",Toast.LENGTH_LONG).show()
            }
        }
        binding.calculateAgain.setOnClickListener {
            binding.bmiView.visibility=View.GONE
            binding.calculateButton.visibility=View.VISIBLE
            binding.weightValue.text.clear()
            binding.heightValue.text.clear()
            binding.weightValue.requestFocus()

        }

    }
    private fun bmiStatusValue(bmi:Double):String{
        lateinit var bmiStatus:String
        if (bmi<18.5){
            bmiStatus="Underweight"
        }
        else if (bmi>18.5&&bmi<25){
            bmiStatus="Normal"
        }
        else if (bmi>25&&bmi<30){
            bmiStatus="Overweight"
        }
        else if(bmi>30.0){
            bmiStatus="Obese"
        }
        return bmiStatus
    }
}