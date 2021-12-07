package com.epam.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epam.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_COUNTER = "KEY_COUNTER"
    }

    private lateinit var binding: ActivityMainBinding
    private var counter = 100_000
    private val counterMax=100_000
    private var counterTopStart = 0
    private var counterTopEnd = 0
    private var counterBottomStart = 0
    private var counterBottomEnd = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val startText = counter.toString() + "/" + counterMax.toString() + " " + getString(R.string.mAh)

        binding.textviewFirst.text = startText

        binding.root.setOnClickListener {
            counter--
            "$counter/$counterMax ${getString(R.string.mAh)}".also { binding.textviewFirst.text = it }
        }

        binding.buttonFirst.setOnClickListener {
            if(counter<counterMax)
            counter++
            "$counter/$counterMax ${getString(R.string.mAh)}".also { binding.textviewFirst.text = it }
        }

        binding.tvTopEnd.setOnClickListener {
            counterTopEnd++
            binding.tvTopEnd.text = counterTopEnd.toString()
        }
        binding.tvTopStart.setOnClickListener {
            counterTopStart++
            binding.tvTopStart.text = counterTopStart.toString()
        }
        binding.tvBottomEnd.setOnClickListener {
            counterBottomEnd++
            binding.tvBottomEnd.text = counterBottomEnd.toString()
        }
        binding.tvBottomStart.setOnClickListener {
            counterBottomStart++
            binding.tvBottomStart.text = counterBottomStart.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt(KEY_COUNTER)
        val newText = counter.toString() + "/" + counterMax.toString() + " " + getString(R.string.mAh)
        binding.textviewFirst.text=newText
    }
}