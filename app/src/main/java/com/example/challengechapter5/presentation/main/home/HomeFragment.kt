package com.example.challengechapter5.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.example.challengechapter5.R
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.databinding.FragmentHomeBinding
import com.example.challengechapter5.presentation.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataUser()
        observeLiveData()
        bindView()
    }

    private fun observeLiveData(){
        viewModel.showUser.observe(viewLifecycleOwner, ::handleShowUser)
        viewModel.openLoginPage.observe(viewLifecycleOwner, ::handleOpenLoginPage)
    }

    private fun bindView(){
        binding.btnLogout.setOnClickListener { viewModel.clearDataUser() }
        binding.ivUser.setOnClickListener{ handleOpenProfilePage() }
    }

    private fun handleShowUser(user: GetUserResponse?) {
        Glide.with(requireContext())
            .load(user?.imageUrl.toString())
            .placeholder(
                AvatarGenerator.AvatarBuilder(requireContext())
                    .setTextSize(50)
                    .setAvatarSize(200)
                    .toSquare()
                    .setLabel(user?.fullName.toString())
                    .build()
            )
            .into(binding.ivUser)
    }

    private fun handleOpenLoginPage(isLoggedOut: Boolean) {
        if(isLoggedOut){
            LoginActivity.startActivity(requireContext())
        }
    }

    private fun handleOpenProfilePage(){
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }
}