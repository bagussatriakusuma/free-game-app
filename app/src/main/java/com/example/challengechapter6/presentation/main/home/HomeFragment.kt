package com.example.challengechapter6.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentHomeBinding
import com.example.challengechapter6.presentation.main.home.adapter.PopularMobaGameAdapter
import com.example.challengechapter6.presentation.main.home.adapter.PopularRacingGameAdapter
import com.example.challengechapter6.presentation.main.home.adapter.RecommendedGameAdapter
import com.example.data.remote.response.main.GetAllGamesResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    companion object {
        const val GAME_ID = "id"
    }
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recommendedGameAdapter: RecommendedGameAdapter
    private lateinit var popularMobaGameAdapter: PopularMobaGameAdapter
    private lateinit var popularRacingGameAdapter: PopularRacingGameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recommendedGames()
        viewModel.popularMobaGames()
        viewModel.popularRacingGames()

        observeLiveData()
        bindAdapter()
        provideDataImageSlider()
    }

    private fun observeLiveData(){
        viewModel.showRecommendedGames.observe(viewLifecycleOwner, ::handleDataRecommendedGames)
        viewModel.showPopularMobaGames.observe(viewLifecycleOwner, ::handleDataPopularMobaGames)
        viewModel.showPopularRacingGames.observe(viewLifecycleOwner, ::handleDataPopularRacingGames)

    }
    private fun bindAdapter(){
        recommendedGameAdapter = RecommendedGameAdapter(object : RecommendedGameAdapter.OnClickListener {
            override fun onClickItem(data: com.example.data.remote.response.main.GetAllGamesResponse) {
                val bundle = Bundle()
                bundle.putInt(GAME_ID, data.id.hashCode())
                findNavController().navigate(R.id.action_homeFragment_to_detailGameFragment, bundle)
            }
        })
        binding.rvRecommendedGames.adapter = recommendedGameAdapter

        popularMobaGameAdapter = PopularMobaGameAdapter(object : PopularMobaGameAdapter.OnClickListener {
            override fun onClickItem(data: com.example.data.remote.response.main.GetAllGamesResponse) {
                val bundle = Bundle()
                bundle.putInt(GAME_ID, data.id.hashCode())
                findNavController().navigate(R.id.action_homeFragment_to_detailGameFragment, bundle)
            }
        })
        binding.rvPopularMobaGames.adapter = popularMobaGameAdapter

        popularRacingGameAdapter = PopularRacingGameAdapter(object : PopularRacingGameAdapter.OnClickListener {
            override fun onClickItem(data: com.example.data.remote.response.main.GetAllGamesResponse) {
                val bundle = Bundle()
                bundle.putInt(GAME_ID, data.id.hashCode())
                findNavController().navigate(R.id.action_homeFragment_to_detailGameFragment, bundle)
            }
        })
        binding.rvPopularRacingGames.adapter = popularRacingGameAdapter
    }

    private fun handleDataRecommendedGames(recommendedGames: List<com.example.data.remote.response.main.GetAllGamesResponse>) {
        recommendedGameAdapter.submitData(recommendedGames)
    }

    private fun handleDataPopularMobaGames(popularMobaGames: List<com.example.data.remote.response.main.GetAllGamesResponse>) {
        popularMobaGameAdapter.submitData(popularMobaGames)
    }

    private fun handleDataPopularRacingGames(popularRacingGames: List<com.example.data.remote.response.main.GetAllGamesResponse>) {
        popularRacingGameAdapter.submitData(popularRacingGames)
    }

    private fun provideDataImageSlider() {
        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.the_finals))
        imageList.add(SlideModel(R.drawable.cs2))
        imageList.add(SlideModel(R.drawable.apex_legends))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }
}