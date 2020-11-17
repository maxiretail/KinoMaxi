package com.ministren.kinomaxi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ministren.kinomaxi.databinding.ActivityMainBinding
import com.ministren.kinomaxi.ui.main.MainPageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.root.id, MainPageFragment())
            .commit()
    }

}