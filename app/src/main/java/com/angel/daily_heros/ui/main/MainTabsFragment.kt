package com.angel.daily_heros.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.angel.daily_heros.R
import com.angel.daily_heros.databinding.MainFragBinding
import com.angel.daily_heros.ui.main.history.HistoryFragment
import com.angel.daily_heros.ui.main.message.MessageFragment
import com.angel.daily_heros.ui.main.qr.history.VisitHistoryFragment
import com.angel.daily_heros.util.EventObserver
import com.angel.daily_heros.util.activityViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainTabsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainModel: MainTabsViewModel
    private lateinit var binding: MainFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::mainModel.isInitialized && ::binding.isInitialized) {
            mainModel.createTabModels()
            inflateTabContentFragments()
            changeContent(mainModel.updateModel())
            binding.apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = mainModel
                actionListener = mainModel
            }


        } else {
            mainModel = activityViewModelProvider(viewModelFactory)
            mainModel.createTabModels()
            binding = MainFragBinding.inflate(inflater, container, false)
                .apply {
                    lifecycleOwner = viewLifecycleOwner
                    viewModel = mainModel
                    actionListener = mainModel
                }

            inflateTabContentFragments()


        }

        mainModel.moveQRScanner.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(MainTabsFragmentDirections.actionMainTabsFragmentToQRScannerFragment())
        })

        mainModel.selectTabIndex.observe(viewLifecycleOwner, Observer {
            val to = it
            changeContent(to)
        })

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun setFirstStatus(model: List<TabModel>) {
        model.forEach {
            binding.root.findViewById<FrameLayout>(it.id).visibility = View.GONE
        }
    }

    private fun showFragment(model: List<TabModel>, index: Int) {
        binding.root.findViewById<FrameLayout>(model[index].id).apply {
            visibility = View.VISIBLE
        }
    }


    private fun changeContent(to: Int) {
        mainModel.tabsModel.value?.let {
            setFirstStatus(it)
            showFragment(it, to)
        }
    }

    private fun inflateTabContentFragments() {
        mainModel.tabsModel.value?.forEachIndexed { index, tabModel ->
            val fragment = createContentFragment(index)
            fragmentManager?.beginTransaction()
                ?.apply {
                    replace(tabModel.id, fragment)
                    commit()
                }
        }
    }


    override fun onStart() {
        super.onStart()
        changeContent(mainModel.updateModel())
    }

    override fun onResume() {
        super.onResume()
        setUpBackButton()

    }

    private fun createContentFragment(position: Int): Fragment {
        return when (mainModel.tabsModel.value!![position].fragment) {
            TabContentFragment.HISTORY_OWNER -> VisitHistoryFragment().apply {
                mainViewModel = mainModel
            }
            TabContentFragment.MY_HISTORY -> HistoryFragment().apply {
                mainViewModel = mainModel
            }
            TabContentFragment.NOTICE -> MessageFragment().apply {
                mainViewModel = mainModel
            }
        }
    }

    private var backPressCnt = 0
    private fun setUpBackButton() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mainModel.selectTabIndex.value == 0) {
                    if (backPressCnt < 1) {
                        backPressCnt++
                        Toast.makeText(
                            requireContext(), getString(R.string.press_two_shutdown),
                            Toast.LENGTH_SHORT
                        ).show()
                        lifecycleScope.launch { delay(3000);backPressCnt = 0 }
                    } else {
                        requireActivity().finish()
                    }
                } else {
                    mainModel.onClickTab(0)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}
