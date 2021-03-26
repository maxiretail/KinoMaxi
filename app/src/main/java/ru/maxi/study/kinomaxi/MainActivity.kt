package ru.maxi.study.kinomaxi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.maxi.study.kinomaxi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.root.id, FilmDetailsFragment())
            .commit()
    }

}