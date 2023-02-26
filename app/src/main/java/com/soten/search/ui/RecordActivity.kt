package com.soten.search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soten.search.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRecordBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}