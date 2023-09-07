package com.example.buildvariants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* https://stackoverflow.com/questions/55518814/how-to-externalise-the-base-url-in-retrofit-for-android*/
        val a = BuildConfig.BASE_URL
        Toast.makeText(this@MainActivity, a, Toast.LENGTH_LONG).show()
    }
}