package com.example.challengechapter6.common

import android.content.Context
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters

class BlurWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    companion object {
        const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
        const val KEY_OUTPUT_URI = "KEY_OUTPUT_URI"
    }

    override fun doWork(): Result {
        val inputImageUriString = inputData.getString(KEY_IMAGE_URI)
        val outputImageUriString = inputData.getString(KEY_OUTPUT_URI)

        if (inputImageUriString == null || outputImageUriString == null) {
            return Result.failure()
        }

        val inputImageUri = Uri.parse(inputImageUriString)
        val outputImageUri = Uri.parse(outputImageUriString)

        return Result.success()
    }
}