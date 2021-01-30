package net.android.breakingbadapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.character_fragment.*
import kotlinx.android.synthetic.main.home_items.view.*
import net.android.breakingbadapp.R
import net.android.breakingbadapp.databinding.CharacterFragmentBinding
import net.android.breakingbadapp.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {

    lateinit var characterFragmentBinding: CharacterFragmentBinding
    val myViewModel: MyViewModel by viewModel()

    companion object {
        var nameCharacter = ""
        var location = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterFragmentBinding = CharacterFragmentBinding.inflate(inflater, container, false)
        return characterFragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fixName = nameCharacter.replace("\\s".toRegex(), "+")
        myViewModel.getCharacterInfo(fixName)
        myViewModel.characterLiveData.observe(viewLifecycleOwner, {
            for (info in it) {
                Glide.with(this)
                    .load(info.img)
                    .into(imageCharacter)
                characterFragmentBinding.home = info
            }
        })
        arrowBtn.setOnClickListener {
            findNavController().navigate(R.id.go_home)
            location = false
        }
    }
}


