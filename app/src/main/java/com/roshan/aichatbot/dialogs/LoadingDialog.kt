package com.roshan.aichatbot.dialogs

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.DialogFragment
import com.roshan.aichatbot.R
import com.roshan.aichatbot.databinding.LoadingDialogBinding
import com.roshan.aichatbot.fragments.ChatFragment

class LoadingDialog : DialogFragment() {
    private var binding: LoadingDialogBinding? = null
    private val duration: Long = 2000

    override fun onStart() {
        super.onStart()

        if (dialog!!.window != null){
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            dialog!!.window!!.setGravity(Gravity.CENTER)
            dialog!!.window!!.setBackgroundDrawableResource(R.color.transparent)
            dialog!!.window!!.setWindowAnimations(R.style.DialogAnimation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoadingDialogBinding.inflate(inflater, container, false)

        binding!!.loadingAnim.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val fragment = ChatFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame, fragment)
            transaction.commit()

            dismiss()
        }, duration)

        return binding!!.root
    }
}