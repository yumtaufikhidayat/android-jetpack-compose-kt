package com.taufik.jetpackcompose.compose.interoperability.ui.detail

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import com.taufik.jetpackcompose.R
import com.taufik.jetpackcompose.compose.interoperability.data.local.entity.NewsEntity
import com.taufik.jetpackcompose.compose.interoperability.ui.ViewModelFactory
import com.taufik.jetpackcompose.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNewsDetailBinding.inflate(layoutInflater) }
    private var newsDetail: NewsEntity? = null
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: NewsDetailViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getParcelableData()
        setContent()
    }

    private fun getParcelableData() {
        newsDetail = intent.getParcelableExtra<NewsEntity>(NEWS_DATA) as NewsEntity
    }

    private fun setContent() {
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    newsDetail?.let { NewsDetailScreen(it, viewModel) }
                }
            }
        }
    }

    companion object {
        const val NEWS_DATA = "data"
    }
}

@Composable
fun NewsDetailContent(
    title: String,
    url: String,
    bookmarkStatus: Boolean,
    updateBookmarkStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = updateBookmarkStatus) {
                        Icon(
                            painter = if (bookmarkStatus) {
                                painterResource(R.drawable.ic_bookmarked_white)
                            } else {
                                painterResource(R.drawable.ic_bookmark_white)
                            },
                            contentDescription = stringResource(R.string.save_bookmark),
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = WebViewClient()
                    }
                },
                update = {
                    it.loadUrl(url)
                }
            )
        }
    }
}

@Composable
fun NewsDetailScreen(
    newsDetail: NewsEntity,
    viewModel: NewsDetailViewModel
) {
    viewModel.setNewsData(newsDetail)
    val bookmarkStatus by viewModel.bookmarkStatus.observeAsState(false)
    NewsDetailContent(
        title = newsDetail.title,
        url = newsDetail.url ?: "",
        bookmarkStatus = bookmarkStatus,
        updateBookmarkStatus = {
            viewModel.changeBookmark(newsDetail)
        })
}