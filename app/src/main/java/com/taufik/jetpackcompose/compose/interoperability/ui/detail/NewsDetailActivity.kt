package com.taufik.jetpackcompose.compose.interoperability.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.taufik.jetpackcompose.R
import com.taufik.jetpackcompose.compose.interoperability.data.local.entity.NewsEntity
import com.taufik.jetpackcompose.compose.interoperability.ui.ViewModelFactory
import com.taufik.jetpackcompose.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNewsDetailBinding.inflate(layoutInflater) }
    private var newsDetail: NewsEntity? = null
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: NewsDetailViewModel by viewModels { factory }
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getParcelableData()
        showToolbarTitle()
        showData()
    }

    private fun getParcelableData() {
        newsDetail = intent.getParcelableExtra<NewsEntity>(NEWS_DATA) as NewsEntity
    }

    private fun showToolbarTitle() {
        supportActionBar?.title = newsDetail?.title
    }

    private fun showData() {
        with(binding.webView) {
            webViewClient = WebViewClient()
            loadUrl(newsDetail?.url.toString())
            newsDetail?.let { viewModel.setNewsData(it) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        viewModel.bookmarkStatus.observe(this) { status ->
            setBookmarkState(status)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            newsDetail?.let { viewModel.changeBookmark(it) }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }

    companion object {
        const val NEWS_DATA = "data"
    }
}