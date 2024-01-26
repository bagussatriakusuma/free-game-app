package com.example.challengechapter6.presentation.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challengechapter6.R
import com.example.challengechapter6.common.convertDate
import com.example.challengechapter6.data.remote.response.main.GetDetailGameResponse
import com.example.challengechapter6.databinding.FragmentDetailGameBinding
import com.example.challengechapter6.presentation.main.bookmark.BookmarkFragment
import com.example.challengechapter6.presentation.main.detail.adapter.ScreenshotsAdapter
import com.example.challengechapter6.presentation.main.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailGameFragment : Fragment() {
    private lateinit var binding: FragmentDetailGameBinding
    private val viewModel: DetailGameViewModel by viewModels()
    private lateinit var screenshotsAdapter: ScreenshotsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameID = arguments?.getInt(HomeFragment.GAME_ID)
        val gameIDBookmark = arguments?.getInt(BookmarkFragment.GAME_ID)

        val selectedGameID = gameID ?: gameIDBookmark

        selectedGameID?.let { viewModel.gameDetails(id = it) }

        observeLiveData()
        bindAdapter()
        bindView()
    }

    private fun observeLiveData(){
        viewModel.showGameDetails.observe(viewLifecycleOwner, ::handleShowGameDetails)
        viewModel.bookmarkClicked.observe(viewLifecycleOwner, ::handleGameId)
        viewModel.isBookmarked.observe(viewLifecycleOwner, ::handleBookmarkStatus)
    }

    private fun bindAdapter(){
        screenshotsAdapter = ScreenshotsAdapter(object : ScreenshotsAdapter.OnClickListener {
            override fun onClickItem(data: GetDetailGameResponse.Screenshots) {
                Glide.with(requireContext())
                    .load(data.image)
                    .into(binding.ivThumbnail)
            }
        })
        binding.rvScreenshots.adapter = screenshotsAdapter
    }

    private fun bindView(){
        binding.containerBack.setOnClickListener { findNavController().popBackStack() }
        binding.icBookmark.setOnClickListener { addGameToBookmark() }
    }

    private fun addGameToBookmark() {
        val gameID = arguments?.getInt(HomeFragment.GAME_ID)
        gameID?.let { viewModel.onBookmarkIconClick(it) }
    }

    private fun handleShowGameDetails(getDetailGameResponse: GetDetailGameResponse?) {
        Glide.with(requireContext())
            .load(getDetailGameResponse?.thumbnail)
            .into(binding.ivThumbnail)
        screenshotsAdapter.submitData(getDetailGameResponse?.screenshots)
        binding.tvTitle.text = getDetailGameResponse?.title
        binding.tvGenre.text = getDetailGameResponse?.genre
        binding.tvPlatform.text = getDetailGameResponse?.platform
        binding.tvDescription.text = getDetailGameResponse?.description
        binding.tvPublisher.text = getDetailGameResponse?.publisher
        binding.tvDeveloper.text = getDetailGameResponse?.developer
        binding.tvReleaseDate.text = convertDate(getDetailGameResponse?.releaseDate.toString())
        binding.tvStatus.text = getDetailGameResponse?.status
    }

    private fun handleGameId(gameID: Int) {
        viewModel.insertToBookmark(gameID)
    }

    private fun handleBookmarkStatus(isBookmarked: Boolean) {
        if (isBookmarked) {
            binding.icBookmark.setImageResource(R.drawable.ic_bookmark_selected2)
        } else {
            binding.icBookmark.setImageResource(R.drawable.ic_bookmark_unselected)
        }
    }
}