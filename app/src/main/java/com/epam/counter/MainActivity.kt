package com.epam.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epam.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_COUNTER = "KEY_COUNTER"
    }

    private lateinit var binding: ActivityMainBinding
    private var counter = 0
    private var counterCenter = 0
    private var counterTopStart = 0
    private var counterTopEnd = 0
    private var counterBottomStart = 0
    private var counterBottomEnd = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewFirst.text = getString(R.string.zero)
        binding.root.setOnClickListener {
            counter++
            "$counter/1000".also { binding.textviewFirst.text = it }
        }

        binding.center.setOnClickListener {
            counterCenter++
            binding.center.text = counterCenter.toString()
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

    }
}