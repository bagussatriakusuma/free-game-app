package com.example.challengechapter5.presentation.main.profile

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.challengechapter5.R
import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.databinding.FragmentProfileBinding
import com.example.challengechapter5.utils.uriToFile
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var uri: String = ""
    private var fileImage: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
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
    }

    private fun bindView(){
        binding.ivUser.setOnClickListener { openImagePicker() }
        binding.btnUpdate.setOnClickListener { handleValidation() }
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
        binding.etName.setText(user?.fullName)
        binding.etPhoneNumber.setText(user?.phoneNumber)
    }

    private fun handleValidation(){
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        if (validator(name, phoneNumber)){
            viewModel.updateDataUser(fileImage, name, phoneNumber)
            handleOpenHomePage()
        }
    }

    private fun handleOpenHomePage(){
        findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
    }

    private fun validator(name: String, phoneNumber: String): Boolean {
        resetErrors()

        return when {
            name.isEmpty() -> {
                binding.tilName.error = "name cannot be empty"
                false
            }
            phoneNumber.isEmpty() -> {
                binding.tilPhoneNumber.error = "Password cannot be empty"
                false
            }
            else -> {
                true
            }
        }
    }

    private fun resetErrors() {
        binding.tilName.error = null
        binding.tilPhoneNumber.error = null
    }

    private fun loadImage(uri: Uri) {
        binding.apply {
            Glide.with(binding.root)
                .load(uri)
                .transform(CenterCrop(), RoundedCorners(12))
                .into(ivUser)
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data
                    uri = fileUri.toString()
                    if (fileUri != null) {
                        fileImage = uriToFile(fileUri, requireContext())
                        loadImage(fileUri)
                    }
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    private fun openImagePicker() {
        ImagePicker.with(this)
            .crop()
            .saveDir(
                File(
                    requireContext().externalCacheDir,
                    "ImagePicker"
                )
            )
            .compress(1024)
            .maxResultSize(
                1080,
                1080
            )
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

}