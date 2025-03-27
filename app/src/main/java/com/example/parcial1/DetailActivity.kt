package com.example.parcial1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.parcial1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Enlace a los datos del main
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val country = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("country", Country::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("country")
        }

        if (country != null) {
            binding.countryName.text = country.name.official
            binding.countryRegion.text = country.region
            binding.countryPopulation.text = "Población: ${country.population}"
            Glide.with(this)
                .load(country.flags.png)
                .into(binding.countryFlag)
        }
    }
}