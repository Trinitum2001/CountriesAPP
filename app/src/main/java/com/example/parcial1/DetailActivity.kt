package com.example.parcial1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
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
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mapWebView.apply {
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            webViewClient = WebViewClient()
        }


        val country =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("country", Country::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("country")
            }

        if (country != null) {
            binding.countryName.text = country.translations.spa.official ?: country.name.official
            binding.countryRegion.text = country.region
            binding.countryPopulation.text = "Población: ${country.population}"
            Glide.with(this)
                .load(country.flags.png)
                .into(binding.countryFlag)

            country.maps.googleMaps.takeIf { it.isNotEmpty() }?.let { mapUrl ->
                try {
                    binding.mapWebView?.loadUrl(mapUrl)
                } catch (e: Exception) {
                    binding.mapWebView?.visibility = View.GONE
                    Log.e("DetailActivity", "Error al cargar mapa", e)
                }
            } ?: run {
                binding.mapWebView?.visibility = View.GONE
            }
        }

    }
    override fun onDestroy() {
        binding.mapWebView?.destroy()
        super.onDestroy()
    }


}