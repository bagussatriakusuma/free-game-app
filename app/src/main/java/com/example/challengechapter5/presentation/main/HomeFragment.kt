package com.example.challengechapter5.presentation.main

import android.content.Context
import android.content.Intent
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
        viewModel.getDataUser2()
        observeLiveData()
        bindView()
    }

    private fun bindView(){
        binding.btnLogout.setOnClickListener { viewModel.clearDataUser() }
    }

    private fun observeLiveData(){
        viewModel.showUser.observe(viewLifecycleOwner){ user ->
            Glide.with(requireContext())
                .load(user.imageUrl.toString())
                .placeholder(
                    AvatarGenerator.AvatarBuilder(requireContext())
                        .setTextSize(50)
                        .setAvatarSize(200)
                        .toSquare()
                        .setLabel(user.fullName.toString())
                        .build()
                )
                .into(binding.ivUser)
        }

        viewModel.openLoginPage.observe(viewLifecycleOwner, ::handleOpenLoginPage)
    }

    private fun handleOpenLoginPage(isLoggedOut: Boolean) {
        if(isLoggedOut){
            LoginActivity.startActivity(requireContext())
        }
    }
}