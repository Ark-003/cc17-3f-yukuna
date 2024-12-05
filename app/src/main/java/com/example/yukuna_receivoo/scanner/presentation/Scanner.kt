package com.example.yukuna_receivoo.scanner.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.yukuna_receivoo.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip


class Scanner : Fragment() {
    private lateinit var cameraView: PreviewView
    private lateinit var chipReceipt: Chip
    private lateinit var btnChoicePhoto: AppCompatImageView
    private lateinit var btnTakePhoto: AppCompatImageView
    private lateinit var btnFlash: AppCompatImageView
    private lateinit var vCameraDenied: LinearLayout
    private lateinit var btnGoToSettings: MaterialButton
    private lateinit var vLoading: FrameLayout
    private lateinit var vLoadingText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scanner, container, false)

        // Initialize UI components
        cameraView = view.findViewById(R.id.cameraView)
        chipReceipt = view.findViewById(R.id.chipReceipt)
        btnChoicePhoto = view.findViewById(R.id.btnChoicePhoto)
        btnTakePhoto = view.findViewById(R.id.btnTakePhoto)
        btnFlash = view.findViewById(R.id.btnFlash)
        vCameraDenied = view.findViewById(R.id.vCameraDenied)
        btnGoToSettings = view.findViewById(R.id.btnGoToSettings)
        vLoading = view.findViewById(R.id.vLoading)
        vLoadingText = view.findViewById(R.id.vLoadingText)

        setupListeners()
        return view
    }

    /**
     * Set up click listeners and initial behavior.
     */
    private fun setupListeners() {
        // Choice photo button
        btnChoicePhoto.setOnClickListener {
            // TODO: Implement logic to pick a photo from the gallery
            showToast("Gallery opened")
        }

        // Take photo button
        btnTakePhoto.setOnClickListener {
            // TODO: Implement logic to capture a photo
            showToast("Photo captured")
        }

        // Flash button
        btnFlash.setOnClickListener {
            // TODO: Implement logic to toggle flash
            toggleFlash()
        }

    }

    /**
     * Handles flash toggling.
     */
    private fun toggleFlash() {
        // TODO: Implement actual flash control with camera APIs
        showToast("Flash toggled")
        btnFlash.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_flash_on)) // Example toggle
    }

    /**
     * Utility function to display a Toast.
     */
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up resources if necessary
    }
}
