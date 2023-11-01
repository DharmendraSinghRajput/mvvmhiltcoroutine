package com.example.mvvmhiltcoroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmhiltcoroutine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
    }
}