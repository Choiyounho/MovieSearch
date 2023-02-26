package com.soten.search.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soten.search.databinding.ActivityMainBinding
import com.soten.search.extension.clicks
import com.soten.search.extension.throttleFirst
import com.soten.search.ui.RecordActivity.Companion.KEY_QUERY
import com.soten.search.ui.RecordActivity.Companion.RESULT_CODE
import com.soten.search.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel by viewModels<MainViewModel>()

    private val launch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_CODE) {
                val query = it.data?.extras?.getString(KEY_QUERY)
                query?.let {
                    binding.searchEditText.setText(it)
                    mainViewModel.searchMovies(it)
                }
            }
        }

    private val movieAdapter by lazy {
        MovieAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()
        bindViews()
        subscribeUi()
    }

    private fun setAdapter() {
        binding.movieRecyclerView.adapter = movieAdapter
    }

    private fun bindViews() {
        bindOnClickSearchButton()
        bindOnClickLatestSearchButton()
        bindScrollListener()
    }

    private fun bindOnClickSearchButton() {
        binding.searchButton.clicks()
            .throttleFirst(THROTTLE_TIME)
            .onEach {
                hideKeyboard()
                mainViewModel.searchMovies(binding.searchEditText.text.toString())
            }.launchIn(lifecycleScope)
    }

    private fun bindOnClickLatestSearchButton() {
        binding.latestSearchButton.clicks()
            .onEach {
                val intent = Intent(this, RecordActivity::class.java)
                launch.launch(intent)
            }.launchIn(lifecycleScope)
    }

    private fun bindScrollListener() {
        binding.movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as? LinearLayoutManager)
                    ?.findLastCompletelyVisibleItemPosition()

                val itemTotalCount = recyclerView.adapter?.itemCount

                lastVisibleItemPosition?.let { lastPosition ->
                    val isLastPosition = lastPosition + 1 == itemTotalCount

                    if (isLastPosition) {
                        mainViewModel.fetchMovies()
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                hideKeyboard()
            }
        })
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.movies.collect {
                    movieAdapter.submitList(it.movies)
                }
            }
        }
    }

    private fun hideKeyboard() {
        if (this.currentFocus != null) {
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)

            binding.searchEditText.clearFocus()
        }
    }
}