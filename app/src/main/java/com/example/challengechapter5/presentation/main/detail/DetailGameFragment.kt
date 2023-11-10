package com.example.challengechapter5.presentation.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challengechapter5.R
import com.example.challengechapter5.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter5.data.remote.response.main.GetDetailGameResponse
import com.example.challengechapter5.databinding.FragmentDetailGameBinding
import com.example.challengechapter5.presentation.main.detail.adapter.ScreenshotsAdapter
import com.example.challengechapter5.presentation.main.home.HomeFragment
import com.example.challengechapter5.presentation.main.home.HomeViewModel
import com.example.challengechapter5.presentation.main.home.adapter.RecommendedGameAdapter
import com.example.challengechapter5.utils.convertDate
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
        gameID?.let { viewModel.gameDetails(id = it) }

        observeLiveData()
        bindAdapter()
        bindView()
    }

    private fun observeLiveData(){
        viewModel.showGameDetails.observe(viewLifecycleOwner, ::handleShowGameDetails)
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
}