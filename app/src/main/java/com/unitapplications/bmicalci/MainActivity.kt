package com.unitapplications.bmicalci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done.setOnClickListener(){

                val weight = et_weight.text.toString()
                val height = et_height.text.toString()

                if (validateInput(weight,height)){


                    val bmi_raw = (weight.toFloat())/((height.toFloat()/100)*(height.toFloat()/100))
                    val bmi = String.format("%.2f",bmi_raw).toFloat()
                    displayResult(bmi)

                }
            }

    }
   private fun validateInput(weight : String?,height : String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else-> return true

        }

    }

    private fun displayResult(bmi: Float) {
        bmi_count.text = bmi.toString()
        var result = ""
        var color = 0

        when{
            bmi<18.50 ->{
                result = "Under Weight"
                color = R.color.u_weight
            }
            bmi in 18.50..24.99->{
                result = "Healthy"
                color = R.color.n_weight
            }
            bmi in 25.00..29.99->{
                result = "Over Weight"
                color = R.color.over_weight
            }
            bmi > 30.00->{
                result = "Obese"
                color = R.color.obese
            }
        }
        status.text = result
        status.setTextColor(ContextCompat.getColor(this,color))

    }
}