package com.angel.daily_heros.ui.main.qr.check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.angel.daily_heros.databinding.CheckListFragBinding
import com.angel.daily_heros.ui.custom.OptionsPickerView
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.util.EventObserver
import com.angel.daily_heros.util.activityViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CheckListFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listener: MainTabsActionListener

    private lateinit var checkListViewModel: CheckListViewModel
    private lateinit var binding: CheckListFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkListViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            CheckListFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = checkListViewModel
                actionListener = checkListViewModel
            }

        checkListViewModel.submit.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(CheckListFragmentDirections.actionCheckListFragmentToMainTabsFragment())
        })

        binding.tvTemperature.setOnClickListener {
            showPicker()
        }


        return binding.root
    }

    private fun showPicker() {
        val singlePicker: OptionsPickerView<Float> =
            OptionsPickerView(requireContext())
        val items = ArrayList<Float>()
        items.add(36.0f)
        items.add(36.1f)
        items.add(36.2f)
        items.add(36.3f)
        items.add(36.4f)
        items.add(36.5f)
        items.add(36.6f)
        items.add(36.7f)
        items.add(36.8f)
        items.add(36.9f)

        items.add(37.0f)
        items.add(37.0f)
        items.add(37.1f)
        items.add(37.2f)
        items.add(37.3f)
        items.add(37.4f)
        items.add(37.5f)
        items.add(37.6f)
        items.add(37.7f)
        items.add(37.8f)
        items.add(37.9f)

        items.add(38.0f)
        items.add(38.1f)
        items.add(38.2f)
        items.add(38.3f)
        items.add(38.4f)
        items.add(38.5f)
        items.add(38.6f)
        items.add(38.7f)
        items.add(38.8f)
        items.add(38.9f)

        items.add(39.0f)
        items.add(39.1f)
        items.add(39.2f)
        items.add(39.3f)
        items.add(39.4f)
        items.add(39.5f)
        items.add(39.6f)
        items.add(39.7f)
        items.add(39.8f)
        items.add(39.9f)

        items.add(40.0f)

        singlePicker.setPicker(items)
        singlePicker.setTitle("")
        singlePicker.setCyclic(false)
        singlePicker.setSelectOptions(0)
        singlePicker.setOnoptionsSelectListener { options1, option2, options3 -> //  singleTVOptions.setText("Single Picker " + items.get(options1));
            checkListViewModel.setDoc(items[options1])
        }
        singlePicker.show()
    }

}