package com.angel.daily_heros.ui.onboarding.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.angel.daily_heros.databinding.SplashFragBinding
import com.angel.daily_heros.util.requestPermissionForCamera
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import okhttp3.internal.wait

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            SplashFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
            }

        lifecycleScope.launchWhenStarted {
            delay(3000)
            requestPermissionForCamera(requireContext()) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainTabsFragment())
            }
        }




        return binding.root
    }


}
