package ru.zinoviewk.mvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.zinoviewk.mvi.R
import ru.zinoviewk.mvi.ui.features.home.HomeFragment

fun Any?.log(message: String) = Log.d("zinoviewkstm", message)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }

}