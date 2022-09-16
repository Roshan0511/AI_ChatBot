package com.roshan.aichatbot.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentActivity
import com.roshan.aichatbot.R
import com.roshan.aichatbot.databinding.FragmentSplashScreenBinding
import com.roshan.aichatbot.dialogs.LoadingDialog
import com.roshan.aichatbot.utilities.Preferences

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        val anim = AnimationUtils.loadAnimation(context, R.anim.anim_fade_in)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        binding.ivSplash.animation = anim
        binding.tvSplash.animation = anim

        Handler(Looper.getMainLooper()).postDelayed({

            if (Preferences.getValues(requireContext(), "gender")!!.isNotEmpty()){
                val dialog = LoadingDialog()
                dialog.isCancelable = false
                dialog.show((context as FragmentActivity).supportFragmentManager, dialog.tag)
            } else {
                val fragment = AskGenderFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.mainFrame, fragment)
                transaction.commit()
            }
        }, 2500)

        return binding.root
    }
}