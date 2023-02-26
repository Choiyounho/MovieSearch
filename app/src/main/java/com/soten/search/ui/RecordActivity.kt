package com.soten.search.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.soten.search.databinding.ActivityRecordBinding
import com.soten.search.ui.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecordActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRecordBinding.inflate(layoutInflater) }

    private val recordViewModel by viewModels<RecordViewModel>()

    private val searchAdapter by lazy {
        SearchAdapter {
            val intent = Intent().apply {
                putExtra(KEY_QUERY, it)
            }
            setResult(RESULT_CODE, intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()
        fetchQueries()
    }

    private fun setAdapter() {
        binding.queryRecyclerView.adapter = searchAdapter
    }

    private fun fetchQueries() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                recordViewModel.queries.collect{
                    Log.e("collect", "수집")
                    searchAdapter.setItems(it)
                }
            }
        }
    }

    companion object {
        const val RESULT_CODE = 1000
        const val KEY_QUERY = "KEY_QUERY"
    }
}