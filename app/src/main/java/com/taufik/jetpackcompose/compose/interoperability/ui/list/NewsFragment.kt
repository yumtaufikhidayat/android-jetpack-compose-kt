package com.taufik.jetpackcompose.compose.interoperability.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.jetpackcompose.R
import com.taufik.jetpackcompose.compose.interoperability.ui.ViewModelFactory
import com.taufik.jetpackcompose.compose.interoperability.ui.detail.NewsDetailActivity
import com.taufik.jetpackcompose.databinding.FragmentNewsBinding
import com.taufik.jetpackcompose.compose.interoperability.data.remote.Result

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var tabName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBundle()
        showData()
    }

    private fun initBundle() {
        tabName = arguments?.getString(ARG_TAB)
    }

    private fun showData() = with(binding) {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val viewModel: NewsViewModel by viewModels {
            factory
        }

        val newsAdapter = NewsAdapter { news ->
            val intent = Intent(activity, NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.NEWS_DATA, news)
            startActivity(intent)
        }

        when (tabName) {
            TAB_NEWS -> {
                viewModel.getHeadlineNews().observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                progressBar.visibility = View.GONE
                                val newsData = result.data
                                newsAdapter.submitList(newsData)
                            }
                            is Result.Error -> {
                                progressBar.visibility = View.GONE
                                viewError.root.visibility = View.VISIBLE
                                viewError.tvError.text = getString(R.string.something_wrong)
                            }
                        }
                    }
                }
            }
            TAB_BOOKMARK -> {
                viewModel.getBookmarkedNews().observe(viewLifecycleOwner) { bookmarkedNews ->
                    newsAdapter.submitList(bookmarkedNews)
                    progressBar.visibility = View.GONE
                    viewError.tvError.text = getString(R.string.no_data)
                    viewError.root.visibility = if (bookmarkedNews.isNotEmpty()) View.GONE else View.VISIBLE
                }
            }
        }

        rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_NEWS = "news"
        const val TAB_BOOKMARK = "bookmark"
    }
}