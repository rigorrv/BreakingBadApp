package net.android.breakingbadapp.ui.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.home_items.view.*
import net.android.breakingbadapp.R
import net.android.breakingbadapp.model.home.HomeJsonItem
import net.android.breakingbadapp.ui.CharacterFragment
import net.android.breakingbadapp.ui.HomeFragment


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var homeList: List<HomeJsonItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.onBind(homeList[position])
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameTxt = itemView.nameTxt
        val characterImage = itemView.characterImage
        fun onBind(homeJson: HomeJsonItem) {
            nameTxt.text = homeJson.name

            Glide.with(itemView)
                .load(homeJson.img)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progressBar.setVisibility(View.GONE)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progressBar.setVisibility(View.GONE)
                        return false
                    }
                })
                .into(characterImage)

            itemView.setOnClickListener {
                CharacterFragment.nameCharacter = homeJson.name
                Log.d("TAG", "onBind: ${homeJson.name}")
                CharacterFragment.location = true
                Navigation.findNavController(itemView).navigate(R.id.go_character)
            }
        }
    }
}