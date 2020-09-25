package com.angel.daily_heros.ui.main.qr.camera

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.angel.daily_heros.R
import com.angel.daily_heros.databinding.QrScannerFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.ui.main.MainTabsFragmentDirections
import com.angel.daily_heros.util.activityViewModelProvider
import com.angel.daily_heros.util.requestPermissionForCamera
import dagger.android.support.DaggerFragment
import me.dm7.barcodescanner.zbar.BarcodeFormat
import javax.inject.Inject


class QRScannerFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listener: MainTabsActionListener

    private lateinit var qrViewModel: QRScannerViewModel
    private lateinit var binding: QrScannerFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            QrScannerFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = qrViewModel
            }

        requestPermissionForCamera(requireContext()) {
            runQRScan()
        }


        return binding.root
    }

    private fun runQRScan() {
        binding.scannerView.setLaserEnabled(false)
        binding.scannerView.setSquareViewFinder(true)
        binding.scannerView.setBorderAlpha(1f)
        binding.scannerView.setMaskColor(Color.parseColor("#66080808"))
        binding.scannerView.setBorderStrokeWidth(resources.getDimensionPixelOffset(R.dimen.qr_border_width))
        binding.scannerView.setBorderColor(Color.parseColor("#ffffff"))
        binding.scannerView.setFormats(List<BarcodeFormat>(1) { BarcodeFormat.QRCODE })
        binding.scannerView.startCamera()

        binding.scannerView.setResultHandler { rawResult ->

            Log.d("barcodeFormat", rawResult.barcodeFormat.id.toString())
            Log.d("barcodeFormat", rawResult.barcodeFormat.name)
            Log.d("barcodeFormat", rawResult.contents)

            binding.scannerView.stopCamera()

            findNavController().navigate(QRScannerFragmentDirections.actionQRScannerFragmentToCheckListFragment())

//            viewModel.onRecognized(rawResult.contents)

            Thread.sleep(1000)
        }
    }


}