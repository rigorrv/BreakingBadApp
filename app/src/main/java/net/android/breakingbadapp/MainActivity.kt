package net.android.breakingbadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.home_fragment.view.*
import net.android.breakingbadapp.ui.CharacterFragment
import net.android.breakingbadapp.ui.HomeFragment
import net.android.breakingbadapp.ui.viewRoot

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (CharacterFragment.location) {
            super.onBackPressed()
            CharacterFragment.location = false
        }
    }
}