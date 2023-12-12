package com.example.challengechapter7.presentation.main.profile

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.challengechapter7.R
import com.example.challengechapter7.common.BlurWorker
import com.example.challengechapter7.common.uriToFile
import com.example.challengechapter7.databinding.FragmentProfileBinding
import com.example.challengechapter7.presentation.auth.login.LoginActivity
import com.example.domain.model.auth.UserData
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
    ): View {
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
        viewModel.openLoginPage.observe(viewLifecycleOwner, ::handleOpenLoginPage)
    }

    private fun bindView(){
        binding.tvChangeProfilePicture.setOnClickListener { openImagePicker() }
        binding.btnUpdate.setOnClickListener { handleValidation() }
        binding.containerBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnLogout.setOnClickListener { viewModel.clearDataUser() }
    }

    private fun handleShowUser(user: UserData?) {
        Glide.with(requireContext())
            .load(user?.picture.toString())
            .placeholder(
                AvatarGenerator.AvatarBuilder(requireContext())
                    .setTextSize(50)
                    .setAvatarSize(200)
                    .toSquare()
                    .setLabel(user?.name.toString())
                    .build()
            )
            .into(binding.ivUser)
        binding.etName.setText(user?.name)
        binding.etPhoneNumber.setText(user?.phoneNumber)
        binding.etCity.setText(user?.city)
        binding.etAddresss.setText(user?.address)

        val outputImageUri = Uri.fromFile(createOutputImageFile())

        val inputImageUri = Uri.parse(user?.picture.toString())
        val blurWorkRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(
                workDataOf(
                    BlurWorker.KEY_IMAGE_URI to inputImageUri.toString(),
                    BlurWorker.KEY_OUTPUT_URI to outputImageUri.toString()
                )
            )
            .build()

        observeWorkManagerJob(blurWorkRequest)

        WorkManager.getInstance(requireContext()).enqueue(blurWorkRequest)
    }

    private fun createOutputImageFile(): File {
        val storageDir: File? = requireContext().externalCacheDir
        return File.createTempFile("blurred_image", ".jpg", storageDir)
    }

    private fun observeWorkManagerJob(blurWorkRequest: OneTimeWorkRequest) {
        WorkManager.getInstance(requireContext())
            .getWorkInfoByIdLiveData(blurWorkRequest.id)
            .observe(viewLifecycleOwner) { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val outputUriString = workInfo.outputData.getString(BlurWorker.KEY_OUTPUT_URI)
                    if (outputUriString != null) {
                        val outputImageUri = Uri.parse(outputUriString)
                        loadImage(outputImageUri)
                    }
                }
            }
    }

    private fun handleOpenLoginPage(isLoggedOut: Boolean) {
        if(isLoggedOut){
            LoginActivity.startActivity(requireContext())
        }
    }

    private fun handleValidation() {
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val city = binding.etCity.text.toString()
        val address = binding.etAddresss.text.toString()

        if (validator(name, phoneNumber, city, address)) {
            if (fileImage != null) {
                viewModel.updateDataUser(fileImage, name, phoneNumber, city, address)
                handleOpenHomePage()
            } else {
                Toast.makeText(requireContext(), "Please select an image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleOpenHomePage(){
        findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
    }

    private fun validator(name: String, phoneNumber: String, city: String, address: String): Boolean {
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
            city.isEmpty() -> {
                binding.tilPhoneNumber.error = "City cannot be empty"
                false
            }
            address.isEmpty() -> {
                binding.tilPhoneNumber.error = "Address cannot be empty"
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