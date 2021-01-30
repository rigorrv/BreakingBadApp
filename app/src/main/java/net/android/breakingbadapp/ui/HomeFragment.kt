package net.android.breakingbadapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import net.android.breakingbadapp.R
import net.android.breakingbadapp.ui.adapters.HomeAdapter
import net.android.breakingbadapp.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel


lateinit var viewRoot: View

class HomeFragment : Fragment() {


    companion object {
        var searchWindow = false
    }

    val homeAdapter = HomeAdapter()
    val myViewMode: MyViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewRoot = inflater.inflate(R.layout.home_fragment, container, false)
        return viewRoot
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchTxt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                myViewMode.getCharacterInfo(searchTxt.text.toString())
                myViewMode.characterLiveData.observe(viewLifecycleOwner, {
                    homeAdapter.homeList = it
                    recyclerHome.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerHome.adapter = homeAdapter
                })
                searchTxt.setText("")
                searchTxt.clearFocus()
                searchWindow = true
                val `in`: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                `in`.hideSoftInputFromWindow(
                    searchTxt.getWindowToken(),
                    0
                )

                true
            } else false
        })

        getInfo()



        switch2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                myViewMode.homeLiveData.observe(viewLifecycleOwner, {
                    it.sortBy { it.appearance?.size }
                    homeAdapter.homeList = it
                    recyclerHome.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerHome.adapter = homeAdapter
                })
            } else {
                myViewMode.homeLiveData.observe(viewLifecycleOwner, {
                    it.sortByDescending { it.appearance?.size }
                    homeAdapter.homeList = it
                    recyclerHome.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerHome.adapter = homeAdapter
                })
            }
        })
    }

    fun getInfo() {
        myViewMode.homeLiveData.observe(viewLifecycleOwner, {
            it.sortByDescending { it.appearance?.size }
            homeAdapter.homeList = it
            recyclerHome.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerHome.adapter = homeAdapter
        })
    }


}

