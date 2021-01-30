package net.android.breakingbadapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.intro_fragment.*
import net.android.breakingbadapp.R
import net.android.breakingbadapp.utility.Connection

class IntroFragment : Fragment() {

    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.intro_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (Connection.isConnected()) {
            Log.d("TAG", "Check fragment")
            val uri: Uri =
                Uri.parse("https://firebasestorage.googleapis.com/v0/b/abt-map-firebase.appspot.com/o/introbreakingbad%2Fintro.mp4?alt=media&token=be223efe-6547-484c-bc5a-3bb3f0f468af")
            videoViewPlayer.setVideoURI(uri)
            videoViewPlayer.requestFocus()
            videoViewPlayer.start()
            videoViewPlayer.setOnCompletionListener {
                Log.d("TAG", "Next frame")
                findNavController().navigate(R.id.intro_to_home)
            }
        } else {
            findNavController().navigate(R.id.intro_to_home)
        }
    }
}