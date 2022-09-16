package com.roshan.aichatbot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.roshan.aichatbot.R
import com.roshan.aichatbot.utilities.Preferences
import com.roshan.aichatbot.databinding.FragmentAskGenderBinding
import com.roshan.aichatbot.dialogs.LoadingDialog

class AskGenderFragment : Fragment() {

    private lateinit var binding: FragmentAskGenderBinding
    private var selectedGender: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAskGenderBinding.inflate(inflater, container, false)

        binding.ivMale.setOnClickListener {
            selectedGender = 1
            binding.ivMale.setBackgroundResource(R.drawable.gender_background_white)
            binding.ivFemale.setBackgroundResource(R.drawable.gender_background_transparent)

            binding.btnContinue.visibility = View.VISIBLE
        }

        binding.ivFemale.setOnClickListener {
            selectedGender = 2
            binding.ivMale.setBackgroundResource(R.drawable.gender_background_transparent)
            binding.ivFemale.setBackgroundResource(R.drawable.gender_background_white)

            binding.btnContinue.visibility = View.VISIBLE
        }

        binding.btnContinue.setOnClickListener {
            when(selectedGender) {
                1 -> continueFun("Male")
                2 -> continueFun("Female")
            }
        }

        return binding.root
    }

    private fun continueFun(gender: String){
        Preferences.setValues(requireContext(), gender, true)

        val dialog = LoadingDialog()
        dialog.isCancelable = false
        dialog.show((context as FragmentActivity).supportFragmentManager, dialog.tag)
    }
}