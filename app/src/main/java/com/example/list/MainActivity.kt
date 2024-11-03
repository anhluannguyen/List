package com.example.list

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listViewResults = findViewById<ListView>(R.id.listViewResults)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            if (input.isEmpty()) {
                textViewError.text = getString(R.string.error_positive_integer)
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = getString(R.string.error_invalid_number)
                return@setOnClickListener
            }

            textViewError.text = "" // Xóa thông báo lỗi nếu nhập đúng
            val selectedOptionId = radioGroup.checkedRadioButtonId

            if (selectedOptionId == -1) {
                textViewError.text = getString(R.string.error_select_number_type)
                return@setOnClickListener
            }

            val results = when (selectedOptionId) {
                R.id.radioEven -> getEvenNumbers(n)
                R.id.radioOdd -> getOddNumbers(n)
                R.id.radioSquare -> getSquareNumbers(n)
                else -> listOf()
            }

            Log.d("Results", results.toString()) // Kiểm tra nội dung của results

            // Hiển thị danh sách kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listViewResults.adapter = adapter
        }
    }

    // Hàm trả về danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = (0..n).filter { it % 2 == 0 }
        Log.d("Even Numbers", evenNumbers.toString())
        return evenNumbers
    }

    // Hàm trả về danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = (1..n).filter { it % 2 != 0 }
        Log.d("Odd Numbers", oddNumbers.toString())
        return oddNumbers
    }

    // Hàm trả về danh sách số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        Log.d("Square Numbers", squares.toString())
        return squares
    }
}
