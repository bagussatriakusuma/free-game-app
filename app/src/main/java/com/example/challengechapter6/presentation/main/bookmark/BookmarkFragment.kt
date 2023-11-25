package com.example.challengechapter6.presentation.main.bookmark

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentBookmarkBinding
import com.example.challengechapter6.presentation.main.bookmark.adapter.BookmarkAdapter
import com.example.data.local.entity.BookmarkEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    companion object {
        const val GAME_ID = "id"
    }
    private lateinit var binding: FragmentBookmarkBinding
    private val viewModel: BookmarkViewModel by viewModels()
    private lateinit var bookmarkAdapter: BookmarkAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
    }

    private fun bindAdapter() {
        bookmarkAdapter = BookmarkAdapter(object : BookmarkAdapter.OnClickListener {
            override fun onClickItem(data: BookmarkEntity) {
                val bundle = Bundle()
                bundle.putInt(GAME_ID, data.id)
                findNavController().navigate(R.id.action_bookmarkFragment_to_detailGameFragment, bundle)
            }
        })
        binding.rvBookmark.adapter = bookmarkAdapter

        viewModel.bookmarkedGames.observe(viewLifecycleOwner) { bookmarkedGames ->
            if (bookmarkedGames != null && bookmarkedGames.isNotEmpty()) {
                Log.d("BookmarkFragment", "Bookmarked games updated: $bookmarkedGames")
                bookmarkAdapter.submitData(bookmarkedGames)
            } else {
                Log.d("BookmarkFragment", "No bookmarked games found")
            }
        }
    }
}