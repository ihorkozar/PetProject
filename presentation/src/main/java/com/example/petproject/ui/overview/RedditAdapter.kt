package com.example.petproject.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.databinding.ViewItemAdmobBinding
import com.example.petproject.databinding.ViewItemBinding
import com.example.petproject.domain.Models
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class RedditAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // A menu item view type.
    private val MENU_ITEM_VIEW_TYPE = 0

    // The banner ad view type.
    private val BANNER_AD_VIEW_TYPE = 1

    private var lastPosition = -1

    var childrenList: List<Models.Children> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val withBinding: ViewItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    RedditViewHolder.LAYOUT,
                    parent,
                    false
                )
                return RedditViewHolder(withBinding)
            }
            1 -> {
                val withBinding: ViewItemAdmobBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    AdMobViewHolder.LAYOUT_AD_MOB,
                    parent,
                    false
                )
                return AdMobViewHolder(withBinding)
            }
            else -> TODO()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RedditViewHolder) {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(childrenList[position])
            }
            holder.binding.also {
                it.children = childrenList[position]
            }
        }
        if (holder is AdMobViewHolder) {
            val adView: AdView = holder.binding.adView
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
        if(holder.adapterPosition > lastPosition){
            val anim = AnimationUtils.loadAnimation(holder.itemView.context ,R.anim.slide_in_row)
            holder.itemView.startAnimation(anim)
            lastPosition = holder.adapterPosition
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 10 == 0) BANNER_AD_VIEW_TYPE else MENU_ITEM_VIEW_TYPE
    }

    override fun getItemCount(): Int = childrenList.size
}

class OnClickListener(val clickListener: (children: Models.Children) -> Unit) {
    fun onClick(children: Models.Children) = clickListener(children)
}

class RedditViewHolder(val binding: ViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.view_item
    }
}

class AdMobViewHolder(val binding: ViewItemAdmobBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        @LayoutRes
        val LAYOUT_AD_MOB = R.layout.view_item_admob
    }
}


